#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdint.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 2021
#define IP "127.0.0.1"

void check_error(int res, char *msg) {
    if(res == -1) {
        perror(msg);
        // rydde?
        exit(EXIT_FAILURE);
    }
}
//lengden - melding
int main(void) {
    int fd, wc;
    struct sockaddr_in dest_addr;
    char *msg = "Dette er en sikker melding med TCP! Hilsen Gaute.";
    char *stream;

    fd = socket(AF_INET, SOCK_STREAM, 0);
    check_error(fd, "socket");

    dest_addr.sin_family = AF_INET;
    dest_addr.sin_port = htons(PORT);
    wc = inet_pton(AF_INET, IP, &dest_addr.sin_addr.s_addr);
    check_error(wc , "inet_pton");
    if (!wc) {
        printf("inet_pton bad adddress format\n");
        //rydde
        exit(EXIT_FAILURE);
    }



    wc = connect(fd, (struct sockaddr*)&dest_addr, sizeof(struct sockaddr_in));
    check_error(wc, "connect");

    stream = malloc(strlen(msg) + 1);
    stream[0] = (unsigned char)strlen(msg);
    memcpy(&stream[1], msg, strlen(msg));

    wc = 0;
    while (wc < (int)strlen(msg)+ 1) {
        wc += write(fd, &stream[wc], strlen(msg) + 1 - wc);
        check_error(wc, "write");
    }

    free(stream);
    close(fd);

    return EXIT_SUCCESS;
}