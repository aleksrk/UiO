#include <stdio.h>
#include <stdlib.h>

/*
 * Snuskedal universitet søknad
 * 8 bits - donasjon
 * 3 bits - karakter
 * 5 bits - ID
 */

#define GRADE_MASK 0b0000000011100000
#define ID_MASK 0b0000000000011111

unsigned char hent_id(unsigned short s) {
    return s & ID_MASK;
}

unsigned char hent_donasjon(unsigned short s) {
    return s >> 8;
}

unsigned char hent_karakter(unsigned short s) {
    return (s & GRADE_MASK) >> 5;
}

void print_soknad(unsigned short s) {
    printf("ID: %d\nKarakter: %d\nDonasjon: %d\n", hent_id(s), hent_karakter(s), hent_donasjon(s));
    printf("Score: %d\n", hent_karakter(s) * hent_donasjon(s));
}

int main(void) {
    unsigned short soknad1 = 0b0000000011001010;
    //               DONASJONKARIDENT
    unsigned short soknad2 = 0b1111010000110010;
    return EXIT_SUCCESS;
}