CFLAGS = -g -std=gnu11 -Wall -Wextra
BIN = client

all: $(BIN)

client: client.o common.o common.h
	gcc $(CFLAGS) client.o common.o -o client

server: server.o common.o common.h
	gcc $(CFLAGS) server.o common.o -o server

client.o: client.c
	gcc $(CFLAGS) -c client.c

server.o: server.c
	gcc $(CFLAGS) -c server.c

common.o: common.c
	gcc $(CFLAGS) -c common.c

clean:
	rm -f $(BIN) *.o