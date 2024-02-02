// array_list.cpp
#include <iostream>
#include <stdexcept>
#include <vector>

class ArrayList {
  private:
    // Array containing the actual data in the list
    int *_data;
    // Capacity of the array
    int _capacity = 1;
    // Size of the array
    int _size = 0;

    /**
     * @brief Resize array with a growth factor of 2.
     * Copy all elements of the original array over to
     * the new array and delete the old array.
     *
     */
    void resize() {
        _capacity *= 2;
        int *new_data = new int[_capacity];
        for (int i = 0; i < _size; i++) {
            new_data[i] = _data[i];
        }
        delete[] _data;
        _data = new_data;
    }

  public:
    // Default constructor
    ArrayList() {
        _data = new int[_capacity];
    }

    // Constructor for a list of values
    ArrayList(std::vector<int> values) {
        if (_capacity < values.size()) {
            _capacity = values.size();
        }
        _data = new int[_capacity];
        for (int value : values) {
            append(value);
        }
    }

    // Destructor
    ~ArrayList() {
        delete[] _data;
    }

    // Length of array
    int length() {
        return _size;
    }

    /**
     * @brief Append element to the end of the list
     *
     * @param n The value to be appended
     */
    void append(int n) {
        if (_size >= _capacity) {
            resize();
        }
        _data[_size] = n;
        _size++;
    }

    /**
     * @brief Get value at a given index.
     * Throws a range error in index if out of bounds
     *
     * @param index The index
     * @return int The value at that index
     */
    int get(int index)
    {
        if ((index < 0) || (index >= _size)) {
            throw std::range_error("Index is out of bounds");
        }
        return _data[index];
    }

    /**
     * @brief Prints the array
     *
     */
    void print() {
        std::cout << "ArrayList([";
        for (int i = 0; i < _size - 1; i++) {
            std::cout << _data[i] << ", ";
        }
        std::cout << _data[_size - 1] << "])\n";
    }

    /**
     * @brief Get a reference to the value at a given index.
     * Throws a range error in index if out of bounds
     *
     * @param index The index
     * @return int The value at that index
     */
    int &operator[](int index) {
        if ((index < 0) || (index >= _size)) {
            throw std::range_error("Index is out of bounds");
        }
        return _data[index];
    }
};
// test_array_list.cpp
#include <cassert>
#include <iostream>

#include "array_list.cpp"

/**
 * @brief Test that an empty array list has length zero
 *
 */
void test_empty_array_has_length_zero() {
    ArrayList a{};
    std::cout << "Test that empty array has length zero";
    assert(a.length() == 0);
    std::cout << " - Success!\n";
}

/**
 * @brief Test the append method and the get method
 *
 */
void test_append_and_get() {
    std::cout << "Test append and get";
    ArrayList a{};
    a.append(42);
    a.append(43);
    assert(a.length() == 2);
    assert(a.get(0) == 42);
    assert(a.get(1) == 43);
    std::cout << " - Success!\n";
}

/**
 * @brief Test that printing works
 *
 */
void test_print() {
    std::cout << "Test print\n";
    ArrayList a{};
    a.append(42);
    a.append(43);
    a.print();
}

/**
 * @brief Test that we can construct an ArrayList from
 * a vector of integers
 *
 */
void test_vector_constructor() {
    std::cout << "Test the vector constructor";
    ArrayList a{{1, 2}};
    assert(a.length() == 2);
    assert(a.get(0) == 1);
    assert(a.get(1) == 2);
    std::cout << " - Success!\n";
}

/**
 * @brief Test the indexing operator []
 * to both getting at setting values
 *
 */
void test_indexing_operator() {
    std::cout << "Test the indexing operator";
    ArrayList a{{1, 2}};
    assert(a[0] == 1);
    assert(a[1] == 2);
    a[0] = 42;
    assert(a[0] == 42);
    std::cout << " - Success!\n";
}

int main() {
    test_empty_array_has_length_zero();
    test_append_and_get();
    test_print();
    test_vector_constructor();
    test_indexing_operator();
}
