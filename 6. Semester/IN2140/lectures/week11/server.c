#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdint.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 2021
#define BUFSIZE 255

void check_error(int res, char *msg) {
    if(res == -1) {
        perror(msg);
        // rydde?
        exit(EXIT_FAILURE);
    }
}

int main(void) {

    int moteplass_fd, msg_fd, rc;
    struct sockaddr_in my_addr;
    char buf[BUFSIZE];
    unsigned char msg_len;

    moteplass_fd = socket(AF_INET, SOCK_STREAM, 0);
    check_error(fd, "socket");

    my_addr.sin_family = AF_INET;
    my_addr.sin_port = htons(PORT);
    my_addr.sin_addr.s_addr = INADDR_ANY;

    rc = bind(moteplass_fd, (struct sockaddr * )&my_addr, sizeof(struct sockaddr_in));
    check_error(rc, "bind");

    // Bytter modus på fd fra å ese og skrive til å bli en møteplass. 
    // AKA tar imot connections og accepter disse
    rc = listen(moteplass_fd, 5); // antallet er maks pending connections
    check_error(rc, "listen");

    msg_fd = accept(moteplass_fd, NULL, NULL);
    check_error(msg_fd, "accept");

    rc = read(msg_fd, &msg_len, sizeof(char));
    check_error(rc, "read");

    rc = 0;
    while (rc < msg_len) {
        rc = read(msg_fd, &buf[rc], msg_len - rc);
        check_error(rc, "read");
    }
    rc = read(msg_fd, buf, BUFSIZE - 1);
    check_error(rc, "read");
    buf[rc] = 0;

    printf("Vi mottok: %s\n", buf);
    close(msg_fd);
    close(moteplass_fd);





    return EXIT_SUCCESS;
}