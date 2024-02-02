#include <stdio.h>
#include <string.h>

struct person {
    int alder; // save by having this as unsigned char to take less space
    char *navn; //better than array takes less space
    long fnr;
};

typedef struct person person;

void fyll_struct(person *p, int alder, char *navn, long fnr) {
    p->alder = alder;
    p->navn = strdup(navn);
    p->fnr = fnr;
}

void free_person(person *p) {
    free(p->navn);
    free(p);
}

int main(void) {

    int i = 5;
    sett_tall(i);
    printf("%d\n", i);

    person *olav = malloc(sizeof(person));
    fyll_struct(&olav, 39, "Olav", 11122331234L);
    //olav.alder = 39;
    //olav.navn = "Olav";

    printf("navn: %s, alder: %d, fnr: %ld\n", olav->navn, olav->alder, olav->fnr);
    //printf("sizeof(struct person): %ld\n", sizeof(struct person));
    free_person(olav);
    return 0;
}