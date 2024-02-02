#include "common.h"

void check_error(int res, char *msg) {
    if (res == -1) {
        perror(msg);
        // rydde=
        exit(EXIT_FAILURE);
    }
}