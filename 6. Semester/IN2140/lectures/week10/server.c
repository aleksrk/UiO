#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

//for header file:
#include "common.h"

//link FILES:


// TCP: Connection based stream of bytes
// UDP: Connection less datagrams

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

    int fd, rc, wc;
    char buf[BUFSIZE];
    struct sockaddr_in my_addr, src_addr;
    socklen_t addr_len;
    char *reply = "Hei tilbake fra server";


    fd = socket(AF_INET, SOCK_DGRAM, 0);
    check_error(fd, "socket");

    my_addr.sin_family = AF_INET;
    my_addr.sin_port = htons(PORT);
    my_addr.sin_addr.s_addr = INADDR_ANY;

    rc = bind(fd, (struct sockaddr*)&my_addr, sizeof(struct sockaddr_in));
    check_error(rc, "bind");

    addr_len = sizeof(struct sockaddr_in);
    rc = recvfrom(fd, 
                buf, 
                BUFSIZE - 1, 
                0, 
                (struct sockaddr*)&src_addr, 
                addr_len);
    check_error(rc, "recv");
    buf[rc] = 0; //'\0'

    printf("Vi mottok: %s\n", buf);

    wc = sendto(fd,
                reply,
                strlen(reply),
                0,
                (struct sockaddr*)&src_addr,
                sizeof(struct sockaddr_in));
    check_error(wc, "sendto");
    close(fd);
    return EXIT_SUCCESS;
}