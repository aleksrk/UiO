#include <cassert>
#include <iostream>

#include "array_list.cpp"
void test_empty_array_has_length_zero() {
    ArrayList a{};
    std::cout << "Test that empty array has length zero\n";
    assert(a.length() == 0);
    std::cout << "Success!\n";
}

int main() {
    test_empty_array_has_length_zero();
}
