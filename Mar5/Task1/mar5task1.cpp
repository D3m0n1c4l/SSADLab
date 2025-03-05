#include <iostream>
#include <vector>

template <typename T>
class CircularBuffer {
public:
    explicit CircularBuffer(size_t capacity) : buffer(capacity), head(0), tail(0), count(0) {}

    void push(const T& value) {
        if (count == buffer.size()) {
            head = (head + 1) % buffer.size();
        } else {
            count++;
        }
        buffer[tail] = value;
        tail = (tail + 1) % buffer.size();
    }

    T pop() {
        if (empty()) {
            throw std::out_of_range("Buffer is empty!");
        }
        T value = buffer[head];
        head = (head + 1) % buffer.size();
        count--;
        return value;
    }

    [[nodiscard]]
    bool empty() const {
        return count == 0;
    }

    [[nodiscard]]
    size_t size() const {
        return count;
    }

    class Iterator {
    public:
        Iterator(CircularBuffer& buffer, const size_t index) : buffer(buffer), index(index) {}

        T& operator*() {
            return buffer.buffer[index];
        }

        T* operator->() {
            return &buffer.buffer[index];
        }

        Iterator& operator++() {
            index = (index + 1) % buffer.size();
            return *this;
        }

        Iterator operator++(int) {
            Iterator temp = *this;
            ++(*this);
            return temp;
        }

        bool operator!=(const Iterator& other) const {
            return index != other.index;
        }

        bool operator==(const Iterator& other) const {
            return index == other.index;
        }

    private:
        CircularBuffer& buffer;
        size_t index;
    };

    class ConstIterator {
    public:
        ConstIterator(const CircularBuffer& buffer, const size_t index) : buffer(buffer), index(index) {}

        const T& operator*() const {
            return buffer.buffer[index];
        }

        const T* operator->() const {
            return &buffer.buffer[index];
        }

        ConstIterator& operator++() {
            index = (index + 1) % buffer.size();
            return *this;
        }

        ConstIterator operator++(int) {
            ConstIterator temp = *this;
            ++(*this);
            return temp;
        }

        bool operator!=(const ConstIterator& other) const {
            return index != other.index;
        }

        bool operator==(const ConstIterator& other) const {
            return index == other.index;
        }

    private:
        const CircularBuffer& buffer;
        size_t index;
    };

    Iterator begin() {
        return Iterator(*this, head);
    }

    Iterator end() {
        return Iterator(*this, (head + count) % buffer.size());
    }

    [[nodiscard]]
    ConstIterator begin() const {
        return ConstIterator(*this, head);
    }

    [[nodiscard]]
    ConstIterator end() const {
        return ConstIterator(*this, (head + count) % buffer.size());
    }

private:
    std::vector<T> buffer;
    size_t head, tail, count;
};

int main() {
    CircularBuffer<int> cb(5);

    // Push elements into the buffer
    cb.push(1);
    cb.push(2);
    cb.push(3);
    cb.push(4);
    cb.push(5);

    // Use iterator to print buffer
    std::cout << "Circular Buffer: ";
    for (auto it = cb.begin(); it != cb.end(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;

    // Push more elements to check circular behavior
    cb.push(6);
    cb.push(7);

    // Use iterator to print buffer again
    std::cout << "After pushing more elements: ";
    for (auto it = cb.begin(); it != cb.end(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;

    // Pop elements from the buffer
    std::cout << "Popped: " << cb.pop() << std::endl;
    std::cout << "Popped: " << cb.pop() << std::endl;

    return 0;
}