#include <iostream>
#include <vector>
using namespace std;

template<typename K, typename V>
class Entry {
public:
    K key;
    V value;
    Entry<K, V>(K k, V v) : key(k), value(v) {}
};

template<typename K, typename V>
class Dictionary {
    std::vector<Entry<K, V>> data;
public:
    Dictionary(std::vector<Entry<K, V>> v) : data(v) {}
    Dictionary() { data.reserve(1000); }

    V get(K key) {
        for (auto it = data.begin(); it != data.end(); it++) {
            if (it->key == key) {
                return it->value;
            }
        }
    }
    void put(K key, V value) {
        data.emplace_back(Entry<K, V>(key, value));
    }
    V remove(K key) {
        for (auto it = data.begin(); it != data.end(); it++) {
            if (it->key == key) {
                V value = it->value;
                data.erase(it);
                return value;
            }
        }

    }
    int size() {
        return data.size();
    }
};

int main() {
    Dictionary<int, int> dict;
    dict.put(1, 1);
    dict.put(2, 2);
    dict.put(3, 3);
    dict.put(4, 4);
    dict.put(5, 5);
    dict.put(6, 6);
    dict.put(7, 7);
    dict.remove(1);
    dict.remove(2);
    dict.remove(3);
    printf("%d\n", dict.size());
    printf("%d\n", dict.get(5));
}
