#include <iostream>
#include <vector>
using namespace std;

template<typename T>
class GenericStack {
public:
    int top;
    int capacity;
    std::vector<T> S;

    explicit GenericStack(int capacity) {
        this->top = 0;
        this->capacity = capacity;
        S.reserve(capacity);
    }

    void push(T x) {
        if (S.size() >= capacity) throw std::overflow_error("Stack overflow");
        S.push_back(x);
        top++;
    }

    T pop() {
        if (S.empty()) throw std::out_of_range("Stack underflow");
        T value = S.back();
        S.pop_back();
        top--;
        return value;
    }

    T peek() const {
        if (S.empty()) throw std::out_of_range("Stack is empty");
        return S.back();
    }

    bool isEmpty() const { return S.empty(); }
};

class StringStack : public GenericStack<string> {
public:
    explicit StringStack(int capacity) : GenericStack(capacity) {}

    void concatTopTwo() {
        if (S.size() < 2) throw std::out_of_range("Not enough elements to concatenate");
        const string s1 = pop();
        const string s2 = pop();
        push(s2 + s1);
    }
};

int main() {
    StringStack stack(10);
    stack.push("Hello");
    stack.push("World");
    stack.push("Wow");
    stack.push("Meow");
    stack.push("Woof");
    stack.pop();

    cout << "Top element before concat: " << stack.peek() << endl;

    stack.concatTopTwo();

    cout << "Top element after concat: " << stack.peek() << endl;

    return 0;
}