#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>

#define BUFSIZE 255

int main(void) {
    int fd, fd2, wc, rc;
    char buf[BUFSIZE];

    fd = open("text.txt", O_WRONLY | O_TRUNC | O_CREAT);
    if (fd == -1) {
        perror("open");
        return EXIT_FAILURE;
    }

    wc = write(fd, "hei", 3);
    if (wc == -1) {
        perror("write");
        close(fd);
        return EXIT_FAILURE;
    }

    fd2 = open("text.txt", O_RDONLY);
    if (fd2 == -1) {
        perror("open");
        return EXIT_FAILURE;
    }

    rc = read(fd, buf , BUFSIZE);
    if (rc == -1) {
        perror("read");
        close(fd2);
        return EXIT_FAILURE;
    }

    buf[rc] = 0;

    printf("I bufferet ligger det: %s\n", buf);

    close(fd);

    return EXIT_SUCCESS;
}