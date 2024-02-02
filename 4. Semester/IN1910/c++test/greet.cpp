#include <iostream>
#include <string>

void greet(std::string name)
{
    std::cout << "Hello there " << name << "!\n";
}

int main()
{
    std::string name;
    std::cout << "What is you name? ";
    std::cin >> name;
    greet(name);
    return 0;
}
