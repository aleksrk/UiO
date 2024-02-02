#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdint.h>
#include <send_packet.h>

struct min_struct {
    char c;
    int i;
    char rest[];
}__attribute__((packed)); // this tells compilator to not fill the struct to x8 bytes, not it should only take 5
                          // this is good for sending packets cause you send less bytes

int main(void) {

    struct min_struct m;
    // memset(&m, 0, sizeof(struct min_struct));
    // memset says at memory address &m and sizeof struct bytes set those to 0, 
    // this overwrites unitialized bytes in the struct to 0, effectively fixing leaks

    m.c = 'a';
    m.i = 65;

    write(STDOUT_FILENO, &m, sizeof(struct min_struct));

    send_packet(STDOUT_FILENO, (char*)&m, sizeof(struct min_struct), 0, NULL, 0); // must be char pointer if not warning

    printf("\n");

    return EXIT_SUCCESS;
}