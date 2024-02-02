#include <stdio.h>

#define BUF_SIZE 255

int main(void) {
    FILE *fil;
    char land[BUF_SIZE], linje[BUF_SIZE];
    int folketall, rc;

    fil = fopen("land.txt", "r");
    if (fil == NULL) {
        perror("fopen");
        return 1;
    }

    while(fgets(linje, BUF_SIZE, fil)) {
        rc = sscanf(linje, "Det bor %d mennesker i %[^\n]", &folketall, land);
        if (rc == 2) {
            //oke
            printf("%d: %s\n", folketall, land);
        } else {
            // not ok
            printf("Linjen mathcer ikke formatet %s\n", linje);
        }
    }
    if (ferror(fil)) {
        fprintf(stderr, "fgets failed\n");
        return 1;
    }

    fclose(fil);

    return 0;
}