#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFSIZE 255

void funk() {
    exit(EXIT_FAILURE);
}

int main(void) {
    FILE *fil;
    char buf[BUFSIZE];
    // char *msg = "dette er en buffered write";
    size_t wc;
    size_t rc;

    fil = fopen("ny_fil.txt", "w");
    if (fil == NULL) {
        perror("fopen");
        return EXIT_FAILURE;
    }

    rc = fread(buf, sizeof(char), BUFSIZE - 1, fil);
    if (rc < BUFSIZE && ferror(fil)) {
        fprintf(stderr, "fread: error occured\n");
        fclose(fil);
        return EXIT_FAILURE;
    }

    buf[rc] = 0;

    printf("buf: %s\n", buf);
    /*
    wc = fwrite(msg, sizeof(char), strlen(msg), fil);
    if (wc < strlen(msg)) {
        fprintf(stderr, "fwrite: short item count\n");
        fclose(fil);
        return EXIT_FAILURE;
    }
    */
    fclose(fil);
    return EXIT_SUCCESS;
}