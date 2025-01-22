#include <iostream>
using namespace std;

void swap1(int* a, int* b) {
    const int temp = *a;
    *a = *b;
    *b = temp;
}

void swap2(int& a, int& b) {
    const int temp = b;
    b = a;
    a = temp;
}

int function() {
    int a1 = 5;
    int b1 = 10;
    int a2 = 5;
    int b2 = 10;

    swap1(&a1, &b1);
    swap1(&a2, &b2);

    cout << a1 << " " << b1 << " " << a2 << " " << b2 << endl;

    return 0;
}