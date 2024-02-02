#include <stdio.h>

/*
 * Printer tall på binaerform
 *
 * void *n - Adressen til tall-variablen som skal printes
 * int size - Størrelsen til tall-variablelen
 * 
*/

void printbits(void *n, int size) {
    char *num = (char *)n;
    int i, j;

    for (i = size-1;i >= 0; i--) {
        for (j = 7; j >= 0; j--) {
            printf("%c", (num[i] & (1 << j)) ? '1' : '0');
        }
        printf(" ");
    }
    printf("\n");
}

void printchar(unsigned char c) {
    printbits(&c, sizeof(char));
}

void print_execution(unsigned char a, unsigned char b, unsigned char c) {
    printf("a: "); printchar(a);
    printf("b: "); printchar(b);
    printf(" = "); printchar(c);
}

int main(void) {
    /*
    unsigned char c = 1 << 7; // the << operand means we move the bits 7 spaces to the left, it shifts the entire number not just one bit,
    //we can also shift it the other way by swapping to >>
    unsigned short s = c;
    unsigned int i = s;

    printbits(&c, sizeof(char));
    printbits(&s, sizeof(short));
    printbits(&i, sizeof(int));
    */
    unsigned char a, b, c;

    a = 0b01101101;
    b = 0b10111001;
    c = a & b; //this is a bitwise & operator that results in true/false from the two bits
    //can also use a|b which is or, nanother true table
    //if we use ^ we get an xor
    //we can use tilde for bitwise not, we flip all the bits using this operatpr

    return 0;
}