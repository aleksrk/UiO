/*
 * This is a file that implements the operation on TCP sockets that are used by
 * all of the programs used in this assignment.
 *
 * *** YOU MUST IMPLEMENT THESE FUNCTIONS ***
 *
 * The parameters and return values of the existing functions must not be changed.
 * You can add function, definition etc. as required.
 */
#include "connection.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <netdb.h>
#include <sys/types.h>

int tcp_connect( char* hostname, int port )
{
    int fd, con;
    struct sockaddr_in server_addr;

    fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd < 0) {
        perror("socket");
        return -1;
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(port);

    con = inet_pton(AF_INET, hostname, &server_addr.sin_addr.s_addr);
    if (con == 0) {
        fprintf(stderr, "inet_pton: Invalid IP address format\n");
        return -1;
    }
    else if (con < 0) {
        perror("inet_pton");
        return -1;
    }

    con = connect(fd, (struct sockaddr *)&server_addr, sizeof(server_addr));
    if (con < 0) {
        perror("connect");
        return -1;
    }

    return fd;
}

int tcp_read( int sock, char* buffer, int n )
{
    ssize_t rc;

    rc = read(sock, buffer, n);
    if (rc < 0) {
        perror("read");
        return -1;
    }

    if (rc == 0) {
        // Socket closed
        return 0;
    }
    
    return rc;
}

int tcp_write( int sock, char* buffer, int bytes )
{
    ssize_t wc;

    wc = write(sock, buffer, bytes);
    if (wc < 0) {
        perror("write");
        return -1;
    }   
    return wc;
}

int tcp_write_loop( int sock, char* buffer, int bytes )
{
    ssize_t wc;
    int wc_total = 0;

    while (bytes > 0) {
        wc = write(sock, buffer + wc_total, bytes);
        if (wc < 0) {
            perror("write");
            return -1;
        }

        wc_total += wc;
        bytes -= wc;
    }

    return wc_total;
}

void tcp_close( int sock )
{
    close(sock);
}

int tcp_create_and_listen( int port )
{
    int listener_fd, rc;
    struct sockaddr_in my_addr;

    listener_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (listener_fd < 0) {
        perror("socket");
        return -1;
    }

    my_addr.sin_family = AF_INET;
    my_addr.sin_port = htons(port);
    my_addr.sin_addr.s_addr = INADDR_ANY;

    rc = bind(listener_fd, (struct sockaddr * )&my_addr, sizeof(struct sockaddr_in));
    if (rc < 0) {
        perror("bind");
        return -1;
    }

    rc = listen(listener_fd, 128);
    if (rc < 0) {
        perror("listen");
        return -1;
    }

    return listener_fd;
}

int tcp_accept( int server_sock )
{
    int connection_fd;

    connection_fd = accept(server_sock, NULL, NULL);
    if (connection_fd < 0) {
        perror("accept");
        return -1;
    }

    return connection_fd;
}

int tcp_wait( fd_set* waiting_set, int wait_end )
{
    int rc;

    rc = select(wait_end + 1, waiting_set, NULL, NULL, NULL);
    if (rc < 0) {
        perror("select");
        return -1;
    }

    return rc;
}

int tcp_wait_timeout( fd_set* waiting_set, int wait_end, int timeout )
{
    int rc;
    struct timeval time_out;

    time_out.tv_sec = timeout;
    time_out.tv_usec = 0;

    rc = select(wait_end + 1, waiting_set, NULL, NULL, &time_out);
    if (rc < 0) {
        perror("select");
        return -1;
    }

    return rc;
}

