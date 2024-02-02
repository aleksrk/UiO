#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdint.h>

struct coffee {
    int volume;
    unsigned char rating;
};

struct coffee kaff1;
struct coffee *kaff2;
struct coffee kaff3[3];
struct coffee *kaff4[3];
struct coffee *kaff5;
struct coffee **kaff6;

int main (void) {
    struct coffee c1, c2, c3;

    kaff1.volume = 5;
    kaff1.rating = 10;

    c1.volume = 8;
    c1.rating = 2;

    kaff2 = &c1;

    kaff3[0].volume = 2;
    kaff3[0].rating = 10;

    kaff4[0] = &c1;
    kaff4[1] = &c2;
    kaff4[2] = &c3;

    kaff4[1]->volume = 99;
    kaff4[1]->rating = 99;

    kaff5 = malloc(sizeof(struct coffee) * 5);
    kaff5[0].volume = 4;
    kaff5[0].rating = 44;

    kaff6 = malloc(sizeof(struct coffee *) * 5);
    kaff6[0] = malloc(sizeof(struct coffee));
    kaff6[0]->volume = 4;
    kaff6[0]->rating = 0;
    kaff6[1] = &c3;
    kaff6[1]->volume = 5;
    kaff6[1]->rating = 10;

    return EXIT_SUCCESS;

}