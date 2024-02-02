#include <stdio.h>

int main(void) {

    char a; // 00000001 byte representasjon
    unsigned char b; //kun positive tall, men kan være dobbel så stor som signed(128 vs 256 på char)
    //kan skrive dette på binærform: 0b1010 er det samme som 10
    char c = 10;
    char d = 0b1010;
    if (a == b) {} // disse verdiene er det samme bare representert anderledes
    char e = 0xfa; // 1111 1010

    unsigned int in = 0xffffffff; // høyeste verdien en int kan ha

    short s;
    int i;

    return 0;
}
