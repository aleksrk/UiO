#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdint.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <sys/select.h>

#define BUFSIZE 325

void get_string(char buf[], int size) {
    char c;

    fgets(buf, size, stdin);
    if (buf[strlen(buf) - 1] == '\n') {
        buf[strlen(buf) - 1] = 0;
    } else {
        while((c = getchar()) != '\n' && c != EOF);
    }
}

void check_error(int res, char *msg) {
    if (res == -1) {
        perror(msg);
        //rydde?
        exit(EXIT_FAILURE);
    }
}

int main(int argc, char const *argv[]) {
    int msg_fd, rc;
    fd_set fds;
    struct sockaddr_in friends_addr, my_addr;
    char *buf[BUFSIZE];

    if (argc < 4) {
        printf("Usage: %s <my port> <friends port> <friends ip>\n", argv[0]);
        return EXIT_SUCCESS;
    }

    msg_fd = socket(AF_INET, SOCK_DGRAM, 0);
    check_error(msg_fd, "socket");

    my_addr.sin_family = AF_INET;
    my_addr.sin_port = htons(atoi(argv[1]));
    my_addr.sin_addr.s_addr = INADDR_ANY;

    rc = bind(msg_fd, (struct sockaddr *)&my_addr, sizeof(struct sockaddr_in));
    check_error(rc, "bind");

    friends_addr.sin_family = AF_INET;
    friends_addr.sin_port = htons(atoi(argv[2]));
    rc = inet_pton(AF_INET, argv[3], &friends_addr.sin_addr.s_addr);
    check_error(rc, "inet_pton");
    if (rc == 0) {
        fprintf(stderr, "IP address not valid %s\n", argv[3]);
        //rydde
        return EXIT_FAILURE;
    }

    /*
        2 ting skal skje (samtidig):
        - Lytte til nettet ,kommer det en meldign fra friend?
        - Lytte til tastaturet, kommer det en melding fra bruker?
    
    */
    // Initialiserer fds til en tom liste
    buf[0] = 0;
    while(strcmp(buf, "q")) {    
        FD_ZERO(&fds);

        // sett inn fd i fds
        FD_SET(msg_fd, &fds);
        FD_SET(STDIN_FILENO, &fds);

        rc = select(FD_SETSIZE, &fds, NULL, NULL, NULL);
        check_error(rc, "select");

        if (FD_ISSET(msg_fd, &fds)) {
            // En mld fra nettet
                // 1 lese mld inn i et buffer
                // 2 printe buffer
            rc = read(msg_fd, buf, BUFSIZE - 1);
            check_error(rc, "read");
            buf[rc] = 0;
            printf("Vi mottok: %s\n", buf);
        }

        if (FD_ISSET(STDIN_FILENO, &fds)) {
            // mld fra tastatur
                // 1 lese mld inn i et buffer
                // 2 sende mld over nett til friend
            get_string(buf, BUFSIZE);
            rc = sendto(msg_fd, buf, strlen(buf), 0, (struct sockaddr *)&friends_addr, sizeof(struct sockaddr_in));
            check_error(rc, "sendto");
        }
    }
       


    
    close(msg_fd);
    return EXIT_SUCCESS;
}