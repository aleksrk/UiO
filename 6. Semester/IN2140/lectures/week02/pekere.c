#include <stdio.h>

int global; //global variable som kan aksesseres i hele programmet, vi vil vanligvis unngå å bruke dette

void min_funksjon() {
    int lokal;
    printf("Lokal: %p\n", &lokal);
}

int main(void) {

    int tall = 5; // tall får plass i minnet og gis verdien 5
    int *peker; // lager en peker med * symbolet

    peker = &tall; // setter peker til å peke på addressen(&) til tall

    *peker += 2; //legger til 2 på det peker peker på(altså tall)

    // min_funksjon(&tall); //da kan funksjonen endre på den originale variablen

    printf("%p\n", peker);

    int tall1;
    int tall2;

    printf("Global: %p\n", &global);
    printf("Main: %p\n", &tall1);
    printf("Main: %p\n", &tall2);
    // her blir forskjellen i addresse på tall1 og 2 kun 4, fordi en int tar 4 bytes

    min_funksjon(); // funksjons int får ca 20 bytes lavere verdi enn main tallene,
                    // dette fordi stacken vokser nedover og ett funksjonskall tar ca 20 bytes i minnet

    int *p;
    *p = 3;
    //ikke initialisert pekeren enda, vi prøver å aksessere 0x0 som vi ikke har tilgang til
    char c = 'a';
    char *p = &c;
    *p += 2;
    //funker fordi alt i maskinen er tall, chars følger ascii tabell og om vi legger til 2 får vi bokstaven c istedenfor
    
    return 0;
}
