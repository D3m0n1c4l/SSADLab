#include <iostream>
using namespace std;

template<typename T>
class Wrapper {
    T value;

public:
    explicit Wrapper(T value) : value(value) {}
    T getValue() {
        return value;
    }
};

template<>
class Wrapper<char*> {
    char* value;
public:
    explicit Wrapper(char* value) : value(value) {}
    unsigned long int getValue() {
        return strlen(value);
    }
};

int main() {
    int a = 100;
    char* b = "HELLO";
    Wrapper w(a);
    Wrapper w1(b);
    cout << w.getValue() << endl;
    cout << w1.getValue() << endl;
}