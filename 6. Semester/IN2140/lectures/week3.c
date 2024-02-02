#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Lager en kopi av original med malloc. Må frees av den som kaller på funksjonen
char *lagKopi(char *original) {
    int i, len = strlen(original) + 1;
    char *kopi = malloc(sizeof(char) * len);
    if (kopi == NULL) {
        fprintf(stderr, "%s\n", "malloc: fault in lagKopi\n");
        exit(1);
    }

    for (i = 0; i < len; i++) {
        kopi[i] = original[i];
    }

    /*
    i = 0;
    while (*original) {
        kopi[i] = *original
        original++;
        i++;
    }
    */

    return kopi;
}


int main(void) {
    char *streng = "hei hei hei";
    char *kopi = lagKopi(streng);

    printf("Her er kopi: %s\n", kopi);

    free(kopi);
    return 0;
}