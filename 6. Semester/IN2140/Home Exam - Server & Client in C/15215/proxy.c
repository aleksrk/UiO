/*
 * This is the main program for the proxy, which receives connections for sending and receiving clients
 * both in binary and XML format. Many clients can be connected at the same time. The proxy implements
 * an event loop.
 *
 * *** YOU MUST IMPLEMENT THESE FUNCTIONS ***
 *
 * The parameters and return values of the existing functions must not be changed.
 * You can add function, definition etc. as required.
 */
#include "xmlfile.h"
#include "connection.h"
#include "record.h"
#include "recordToFormat.h"
#include "recordFromFormat.h"

#include <arpa/inet.h>
#include <sys/errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <string.h>

/* This struct should contain the information that you want
 * keep for one connected client.
 */
struct Client
{
    char id;
    char identifier;
    int socket;
};

typedef struct Client Client;
#define MAX_CLIENTS 27

Client* clients[MAX_CLIENTS];

void usage( char* cmd )
{
    fprintf( stderr, "Usage: %s <port>\n"
                     "       This is the proxy server. It takes as imput the port where it accepts connections\n"
                     "       from \"xmlSender\", \"binSender\" and \"anyReceiver\" applications.\n"
                     "       <port> - a 16-bit integer in host byte order identifying the proxy server's port\n"
                     "\n",
                     cmd );
    exit( -1 );
}

int calculateBinaryRecordLength(uint8_t flags, char* buffer) {
    int len = 1;  // start with 1 for the flags byte itself
    if (flags & FLAG_SRC) {
        // Source present
        len += 1;
    }
    if (flags & FLAG_DST) {
        // Destination present
        len += 1;
    }
    if (flags & FLAG_USERNAME) {
        // Username present
        int username_length = ntohl(*(int*)(buffer + len));
        len += 4 + username_length;  // Add 4 for the length field itself
    }
    if (flags & FLAG_ID) {
        // ID present
        len += 4;
    }
    if (flags & FLAG_GROUP) {
        // Group present
        len += 4;
    }
    if (flags & FLAG_SEMESTER) {
        // Semester present
        len += 1;
    }
    if (flags & FLAG_GRADE) {
        // Grade present
        len += 1;
    }
    if (flags & FLAG_COURSES) {
        // Courses present
        len += 2;
    }
    return len;
}
/*
 * This function is called when a new connection is noticed on the server
 * socket.
 * The proxy accepts a new connection and creates the relevant data structures.
 *
 * *** The parameters and return values of this functions can be changed. ***
 */
void handleNewClient( int server_sock )
{
    // Accept the new client connection
    int client_sock = tcp_accept(server_sock);
    char buffer[2];

    // Allocate memory for the new client
    Client *client = malloc(sizeof(client));
    if (!client) {
        perror("malloc");
        return;
    }

    // Set the client socket
    client->socket = client_sock;

    // Read the clients identifier
    int rc = tcp_read(client_sock, buffer, sizeof(buffer) - 1);
    if (rc <= 0) {
        close(client_sock);
        free(client);
        return;
    } else {
        buffer[rc] = 0;
        client->identifier = buffer[0];
    }

    // Read the clients id
    rc = tcp_read(client_sock, buffer, sizeof(buffer) - 1);
    printf("Client %c connected.\n", buffer[0]);
    if (rc <= 0) {
        close(client_sock);
        free(client);
        return;
    } else {
        buffer[rc] = 0;
        client->id = buffer[0];
    }

    // Add the client to the list of clients
    for (int i = 0; i < MAX_CLIENTS; i++) {
        if (!clients[i]) {
            clients[i] = client;
            break;
        }
    }
}

/*
 * This function is called when a connection is broken by one of the connecting
 * clients. Data structures are clean up and resources that are no longer needed
 * are released.
 *
 * *** The parameters and return values of this functions can be changed. ***
 */
void removeClient( Client* client )
{
    // Close the client's socket and remove it from the list of clients
    tcp_close(client->socket);
    printf("Client %c disconnected\n", client->id);
    for (int i = 0; i < MAX_CLIENTS; i++) {
        if (clients[i] == client) {
            clients[i] = NULL;
            break;
        }
    }

    // Free the memory reserved for the client
    free(client);
}

/*
 * This function is called when the proxy received enough data from a sending
 * client to create a Record. The 'dest' field of the Record determines the
 * client to which the proxy should send this Record.
 *
 * If no such client is connected to the proxy, the Record is discarded without
 * error. Resources are released as appropriate.
 *
 * If such a client is connected, this functions find the correct socket for
 * sending to that client, and determines if the Record must be converted to
 * XML format or to binary format for sendig to that client.
 *
 * It does then send the converted messages.
 * Finally, this function deletes the Record before returning.
 *
 * *** The parameters and return values of this functions can be changed. ***
 */
void forwardMessage( Record* msg )
{
    // Find the client to send the message to
    Client *dest_client = NULL;
    for (int i = 0; i < MAX_CLIENTS; i++) {
        if (clients[i] && clients[i]->id == msg->dest) {
            dest_client = clients[i];
            break;
        }
    }

    // If the client is not connected, discard message
    if (!dest_client) {
        deleteRecord(msg);
        return;
    }

    // Convert and send message to client
    int bufSize;
    char *buffer;
    if (dest_client->identifier == 'X') {
        buffer = recordToXML(msg, &bufSize);
    } else {
        buffer = recordToBinary(msg, &bufSize);
    }
    if (buffer) {
        tcp_write_loop(dest_client->socket, buffer, bufSize);
        free(buffer);
    }

    // Delete the record once the message is sent
    deleteRecord(msg);
}

/*
 * This function is called whenever activity is noticed on a connected socket,
 * and that socket is associated with a client. This can be sending client
 * or a receiving client.
 *
 * The calling function finds the Client structure for the socket where acticity
 * has occurred and calls this function.
 *
 * If this function receives data that completes a record, it creates an internal
 * Record data structure on the heap and calls forwardMessage() with this Record.
 *
 * If this function notices that a client has disconnected, it calls removeClient()
 * to release the resources associated with it.
 *
 * *** The parameters and return values of this functions can be changed. ***
 */
void handleClient( Client* client )
{
    // Allocate a buffer to read data from the client
    char* buffer = malloc(4096);
    memset(buffer, 0, 4096);
    int bytesread = tcp_read(client->socket, buffer, 4096);
    
    // If no data is read, remove the client
    if (bytesread <= 0) {
        removeClient(client);
        free(buffer);
        return;
    }
    
    // Process the clients data based on the identifier
    if (client->identifier == 'X') {

        char *start = NULL;
        char *stop = NULL;
        char *tmp = buffer;
        Record *record = NULL;
        
        while((start = strstr(tmp, "<record>")) && (stop = strstr(start, "</record>"))) {
            int len = stop - start + strlen("</record>");
            char* tmp_buffer = malloc(len + 1);
            strncpy(tmp_buffer, start, len);
            tmp_buffer[len] = '\0';
            record = XMLtoRecord(tmp_buffer, len, &bytesread);
            if (record) {
                forwardMessage(record);
            }
            tmp = stop + strlen("</record>");
            free(tmp_buffer);
        }
    } else {
        char *tmp = buffer;
        Record *record = NULL;
        while (tmp < (buffer + bytesread)) {
            uint8_t flags = (uint8_t)*tmp;
            int len = calculateBinaryRecordLength(flags, tmp);
            record = BinaryToRecord(tmp, len, &bytesread);
            if (record) {
                forwardMessage(record);
            }
            tmp += len;
        }
    }
    free(buffer);
}

int main( int argc, char* argv[] )
{
 
    int port, server_sock, time_out;
    // Check for correct number of arguments
    if( argc != 2 )
    {
        usage( argv[0] );
    }

    // Create a TCP server socket and listen for connections
    port = atoi( argv[1] );
    server_sock = tcp_create_and_listen( port );
    if( server_sock < 0 ) exit( -1 );

    // Initialize the client list
    for (int i = 0; i < MAX_CLIENTS; i++) {
        clients[i] = NULL;
    }
    
    /*
     * The following part is the event loop of the proxy. It waits for new connections,
     * new data arriving on existing connection, and events that indicate that a client
     * has disconnected.
     *
     * This function uses handleNewClient() when activity is seen on the server socket
     * and handleClient() when activity is seen on the socket of an existing connection.
     *
     * The loops ends when no clients are connected any more.
     */
    do
    {
        // Set up file descriptor set for select()
        fd_set read_fds;
        FD_ZERO(&read_fds);
        FD_SET(server_sock, &read_fds);
        int max_fd = server_sock;
        for (int i = 0; i < MAX_CLIENTS; i++) {
            if (clients[i]) {
                FD_SET(clients[i]->socket, &read_fds);
                if (clients[i]->socket > max_fd) {
                    max_fd = clients[i]->socket;
                }
            }
        }

        // Wait for socket activity
        time_out = tcp_wait_timeout(&read_fds, max_fd, 30);
        if (time_out < 0) {
            break;
        }

        // Handle new client connections
        if (FD_ISSET(server_sock, &read_fds)) {
            handleNewClient(server_sock);
        }

        // Handle existing client connections
        for (int i = 0; i < MAX_CLIENTS; i++) {
            if (clients[i] && FD_ISSET(clients[i]->socket, &read_fds)) {
                handleClient(clients[i]);
            }
        }
    } while( time_out > 0 ); // End when no clients are active for 30 seconds

    printf("No response from clients for 30 seconds, the proxy is now closing\n");

    // Clean up remaining client connections
    for (int i = 0; i < MAX_CLIENTS; i++) {
        if (clients[i]) {
            removeClient(clients[i]);
        }
    }

    // Close listen socket
    tcp_close( server_sock );

    return 0;
}

