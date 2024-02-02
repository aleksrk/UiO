#include <iostream>

double F2C(double F)
{
    return 5*(F-32)/9;
}

int main()
{
    double temp = 100.0;
    std::cout << temp << " F" << endl;
    std::cout << F2C(temp) << " C" << endl;
    return 0;
}
