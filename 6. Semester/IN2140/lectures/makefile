CFLAGS = -std=gnu11 -g -Wall -Wextra
VFLAGS = --leak-check=full --show-leak-kinds=alle --track-origins=yes --malloc-fill=0x40 --free-fill=0x23

all: test

test: test.c
	gcc $(CFLAGS) test.c -o test

check: test
	valgrind $(VFLAGS) ./test

clean:
	rm -f test
