#include <stdio.h>
#include <string.h>

void flipCase(char *str) {
    for (size_t i = 0; i < strlen(str); i++) {
        str[i] = str[i] ^ (1 << 5);
    }
}

int main(void) {
    unsigned char bokstaver[] = {195, 226, 242, 225, 161, 128};

    int i;

    for (i = 0; bokstaver[i] != 128; i++) {
        bokstaver[i] = bokstaver[i] & TILDE(1 << 7);
    }

    bokstaver[i] = bokstaver[i] & ~(1 << 7);

    printf("%s\n", (char*)bokstaver);

    char str[] = "HELloCBrA";
    flipCase(str);
    printf("%s\n", str);

    return 0;
}