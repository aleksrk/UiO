#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdint.h>

struct vare {
    int beholdning;
    int registrert_inn_ut;
    char *navn;
    struct vare *neste;
};

enum type{
    BEHOLDNING, 
    LEVERING,
    SALG
};

struct vare *varer = NULL;

void free_vare(struct vare *v) {
    free(v->navn);
    free(v);
}

void free_varer() {
    struct vare *tmp, *tmp2;

    for (tmp = varer; tmp != NULL; tmp = tmp2) {
        tmp2 = tmp->neste;
        free_vare(tmp);
    }
}

void print_vare(struct vare *v) {
    int svinn = v->registrert_inn_ut - v->beholdning;
    printf("svinn: %d : %s\n", svinn, v->navn);
}

void print_varer() {
    struct vare *tmp;

    for (tmp = varer; tmp != NULL; tmp = tmp->neste) {
        print_vare(tmp);
    }
}

void lag_vare(char *navn, unsigned char antall, enum type t) {
    struct vare *v = malloc(sizeof(struct vare));

    if (v == NULL) {
        fprintf(stderr, "malloc failed, possibly out of memory\n");
        free_varer();
        exit(EXIT_FAILURE);
    }
    v->navn = strdup(navn);
    if (v->navn == NULL) {
        perror("strdup");
        free_varer();
        free(v);
        exit(EXIT_FAILURE);
    }
    v->beholdning = 0;
    v ->registrert_inn_ut = 0;

    switch(t) {
        case BEHOLDNING:
            v->beholdning = antall;
            break;
        case LEVERING:
            v->registrert_inn_ut = antall;
            break;
        case SALG:
            v->registrert_inn_ut = -antall;
            break;
    }

    v->neste = varer;
    varer = v;
 }

 void legg_til_vare(char *navn, unsigned char antall, enum type t) {
    struct vare *tmp;

    tmp = varer;
    while (tmp != NULL) {
        if (strcmp(tmp->navn, navn)) {
            tmp = tmp->neste;
            continue;
        }
        switch(t) {
            case BEHOLDNING:
                tmp->beholdning = antall;
                return;
            case LEVERING:
                tmp->registrert_inn_ut += antall;
                return;
            case SALG:
                tmp->registrert_inn_ut -= antall;
                return;
        }
        tmp = tmp->neste;
    }

    lag_vare(navn, antall, t);
 }

void les_log(char *filnavn, enum type t) {
    FILE *fil;
    char navn[256];
    unsigned char antall, navn_len;
    int rc;

    fil = fopen(filnavn, "rb");
    if (fil == NULL) {
        perror("fopen");
        free_varer();
        exit(EXIT_FAILURE);
    }


    while (fread(&antall, sizeof(char), 1, fil)) {
        if(fread(&navn_len, sizeof(char), 1, fil) == 0) break;
        rc = fread(navn, sizeof(char), navn_len, fil);
        if (rc != navn_len) break;
        navn[navn_len] = 0;

        legg_til_vare(navn, antall, t);
    }

    if (ferror(fil)) {
        fprintf(stderr, "fread failed\n");
        free_varer();
        exit(EXIT_FAILURE);
    }


    if (fclose(fil) == EOF) {
        perror("fclose");
        free_varer();
        exit(EXIT_FAILURE);
    };
}
int main(void) {
    les_log("beholdning.log", BEHOLDNING);
    les_log("levering.log", LEVERING);
    les_log("salg.log", SALG);
    print_varer();
    free_varer();

    return EXIT_SUCCESS;
}