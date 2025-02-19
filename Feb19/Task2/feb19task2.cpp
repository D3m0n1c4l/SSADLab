#include <iostream>
using namespace std;

class Box final {
public:
    int value;
    explicit Box(const int value) {
        this->value = value;
    }
    ~Box() = default;
};

void create_unique() {
    // Printing the value using a unique ptr
    std::unique_ptr<Box> box = std::make_unique<Box>(1);
    printf("UNIQUE value: %d\n", box->value);

    // Change ownership
    const std::unique_ptr<Box> box2 = std::move(box);
    printf("UNIQUE value (ownership transferred): %d\n", box2->value);
}

void create_shared_boxes() {
    // Printing values using shared pointers
    Box box1(1);
    Box box2(2);
    std::shared_ptr<Box> shared1 = std::make_shared<Box>(box1);
    std::shared_ptr<Box> shared2 = std::make_shared<Box>(box2);

    printf("SHARED value 1: %d\n", shared1->value);
    printf("SHARED value 2: %d\n", shared2->value);

    // Printing values after changes
    Box box3(3);
    std::shared_ptr<Box> shared3 = std::make_shared<Box>(box3);
    shared1.reset();
    shared1 = std::make_shared<Box>(box3);
    shared2 = std::make_shared<Box>(box1);

    printf("SHARED value 1 (after swap): %d\n", shared1->value);
    printf("SHARED value 2 (after swap): %d\n", shared2->value);
    printf("SHARED value 3 (after swap): %d\n", shared3->value);
}

void create_weak_ptr() {
    // Creating shared pointers and yielding a circular dependency
    Box box(10);
    std::shared_ptr<Box> shared = std::make_shared<Box>(box);
    std::shared_ptr<Box> shared2 = shared;
    std::shared_ptr<Box> shared3 = shared2;
    shared = shared3;

    printf("WEAK value 1: %d\n", shared->value);
    printf("WEAK value 2: %d\n", shared2->value);
    printf("WEAK value 3: %d\n", shared3->value);

    // Working with pointers using weak pointers
    std::weak_ptr<Box> weak = shared;
    std::weak_ptr<Box> weak2 = shared2;
    std::weak_ptr<Box> weak3 = shared3;
    printf("WEAK value 1 (after resolving CD): %d\n", weak.lock()->value);
    printf("WEAK value 2 (after resolving CD): %d\n", weak2.lock()->value);
    printf("WEAK value 3 (after resolving CD): %d\n", weak3.lock()->value);
}

int main() {
    create_unique();
    create_shared_boxes();
    create_weak_ptr();
}