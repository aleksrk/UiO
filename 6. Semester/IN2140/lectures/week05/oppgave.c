#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFSIZE 255

int main(void) {
    FILE *f;
    char *name;

    char buf[BUFSIZE];
    size_t rc, wc;

    f = fopen("text.txt", "r");
    if (f == NULL) {
        perror("fopen");
        return EXIT_FAILURE;
    }

    rc = fread(buf, sizeof(char), BUFSIZE - 100, f);
    if (rc < BUFSIZE - 100 && ferror(f)) {
        fprintf(stderr, "fread: error occured \n");
        fclose(f);
        return EXIT_FAILURE;
    } 
    fclose(f);

    buf[rc] = 0;
    //printf("%s\n", buf);

    name = strstr(buf, "name");
    if (name == NULL) {
        fprintf(stderr, "fant ikke 'name, feil i fil\n");
        return EXIT_FAILURE;
    }

    //printf("buf: %p\nname: %p\n", buf, name);
    strcpy(name, "Gaute");
    strcat(name, "!");

    //printf("%s\n", name);

    f = fopen("text2.txt", "w");
    if (f == NULL) {
        perror("fopen");
        return EXIT_FAILURE;
    }

    wc = fwrite(buf, sizeof(char), strlen(buf), f);
    if (wc < strlen(buf)) {
        fprintf(stderr, "fwrite: short item count\n");
        fclose(f);
        return EXIT_FAILURE;
    }

    fclose(f);

    return EXIT_SUCCESS;
}