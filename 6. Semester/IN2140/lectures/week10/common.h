#ifndef MY_COMMONS
#define MY_COMMONS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 2021
#define IP "127.0.0.1"
#define BUFSIZE 255
void check_error(int res, char *msg);
#endif