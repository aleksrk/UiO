#include <stdio.h>
#include <stdlib.h>

// struct bil {
//     int hjul;
//     char *merke;

//     unsigned char flags;
// };

/*
 * unsigned char
 * 6 bit = elektrisk
 * 7 bit = eu_godkjent
*/

//#define IS_SET(flags, pos) (flags & (1 << pos))

int is_set(unsigned char flags, int pos) {
    return flags & (1 << pos);
}

void set_flag(unsigned char *flags, int pos) {
    *flags = *flags | (1 << pos);
    // kan også skrive *flags |= (1 << 5);
    //              0b00100000
}

void unset_flag(unsigned char *flags, int pos) {
    *flags &= TILDE(1 << pos);
}

int main(void) {

    unsigned char bil = 0b01001101;

    set_flag(&bil, 5);
    unset_flag(&bil, 5);

    if (is_set(bil, 5)) {
        printf("Bilen er elektrisk\n");
    } else {
        printf("Bilen er ikke elektrisk\n");
    }

    return EXIT_SUCCESS;
}