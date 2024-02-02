#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

// TCP: Connection based stream of bytes
// UDP: Connection less datagrams

// 127.0.0.1 localhost
#define PORT 2021
#define IP "127.0.0.1"
#define BUFSIZE 255

void check_error(int res, char *msg) {
    if (res == -1) {
        perror(msg);
        // rydde=
        exit(EXIT_FAILURE);
    }
}

int main(void) {

    int fd, wc, rc;
    char *msg = "Cbra sender over localhost!";
    char *buf[BUFSIZE];
    struct sockaddr_in dest_addr;
    struct in_addr ip_addr;
    

    fd = socket(AF_INET, SOCK_DGRAM, 0);
    check_error(fd, "socket");

    wc = inet_pton(AF_INET, IP, &ip_addr.s_addr);
    check_error(wc, "inet_pton");
    if (!wc) {
        fprintf(stderr, "Invalid IP address %s\n", IP);
        return EXIT_FAILURE;
    }
    dest_addr.sin_family = AF_INET;
    dest_addr.sin_port = htons(PORT);
    dest_addr.sin_addr = ip_addr;

    wc = sendto(fd, 
                msg, 
                strlen(msg), 
                0, 
                (struct sockaddr*)&dest_addr, 
                sizeof(struct sockaddr_in));
    check_error(wc, "sendto");

    rc = recv(fd, buf, BUFSIZE - 1, 0);
    check_error(rc, "recv");

    buf[rc] = 0;
    printf("Vi fikk som svar: %s\n", buf);
    close(fd);
    return EXIT_SUCCESS;
}