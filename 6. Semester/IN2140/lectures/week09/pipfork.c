#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>

#define READFD 0
#define WRITEFD 1
#define BUFSIZE 255

void check_failure(int res, char *msg) {
    if (res == -1) {
        perror(msg);
        // rydde?
        exit(EXIT_FAILURE);
    }
}

void child_main(int readfd) {
    //printf("%d: This is child printing\n", getpid());

    char buf[BUFSIZE];
    memset(buf, 0, BUFSIZE);
    int rc;
    while(strcmp(buf, "q")) {
        rc = read(readfd, buf, BUFSIZE - 1);
        check_failure(rc, "read");
        buf[rc] = 0;
        printf("Barn mottok: %s\n", buf);
    }

    close(readfd);
}

void parent_main(int writefd) {
    //printf("%d: This is parent printing\n", getpid());

    int wc;
    char msg[BUFSIZE];
    memset(msg, 0, BUFSIZE);

    while(strcmp(msg, "q")) {
        // lese fra stdin, skrive til child
        fgets(msg, BUFSIZE, stdin);
        msg[strlen(msg) - 1] = 0;
        wc = write(writefd, msg, strlen(msg));
        check_failure(wc, "write");
    }



    close(writefd);
}

int main(void) {

    pid_t is_parent;
    int fds[2];

    check_failure(pipe(fds), "pipe");

    is_parent = fork();
    if (is_parent) {
        check_failure(is_parent, "fork");
        close(fds[READFD]);
        parent_main(fds[WRITEFD]);
    } else {
        close(fds[WRITEFD]);
        child_main(fds[READFD]);
    }

    return 0;
}