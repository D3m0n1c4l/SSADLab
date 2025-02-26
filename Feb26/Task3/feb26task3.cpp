#include <iostream>
#include <vector>
using namespace std;

template <typename T>
std::vector<T> map(const std::vector<T>& vec, int (*func)(T)) {
    std::vector<T> result;
    result.reserve(vec.size());
    for (const auto& element : vec) {
        result.push_back(func(element));
    }
    return result;
}

template <typename T>
std::vector<T> filter(const std::vector<T>& vec, std::function<bool(T)> predicate) {
    std::vector<T> result;
    for (const auto& element : vec) {
        if (predicate(element)) {
            result.push_back(element);
        }
    }
    return result;
}

int main() {
    vector<int> nums = {1, 2, 3, 4, 5};
    auto squared = map<int>(nums, [](int a) { return a * a; });
    auto odds = filter<int>(nums, [](int a) { return a % 2; });
    for (const auto& element : squared) {
        cout << element << " ";
    }
    cout << endl;
    for (const auto& element : odds) {
        cout << element << " ";
    }
    return 0;
}
