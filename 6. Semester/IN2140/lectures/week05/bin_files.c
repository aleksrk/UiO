#include <stdio.h>
#include <stdlib.h>

#define ARRSIZE 4

int main(void) {
    FILE *f;
    unsigned char array[] = {1,16,64,255};
    size_t wc;

    f = fopen("my_bin_file.min_ext", "wb");
    if (f == NULL) {
        perror("fopen");
        return EXIT_FAILURE;
    }

    wc = fwrite(array, sizeof(unsigned char), ARRSIZE, f);
    if (wc < ARRSIZE) {
        fprintf(stderr, "fwrite: short item count\n");
        fclose(f);
        return EXIT_FAILURE;
    }


    fclose(f);
    return EXIT_SUCCESS;
}