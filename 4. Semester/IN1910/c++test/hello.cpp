#include <iostream>

int main()
{
    int a = 4;
    // En peker som peker på a
    int *p = &a;
    // Hent ut verdien ved å dereferere pekeren
    int b = *p;
    // Endre verdien som pekes på ved å dereferere pekeren
    *p += 1;
    std::cout << a << std::endl;
    std::cout << b << std::endl;
    std::cout << *p << std::endl;
    return 0;

}
