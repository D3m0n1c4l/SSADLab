#include <iostream>
#include <vector>
#include <string>

using namespace std;

template<typename K, typename V>
class Entry {
public:
    K key;
    V value;
    Entry(K k, V v) : key(k), value(v) {}
};

template<typename K, typename V>
class Dictionary {
    vector<Entry<K, V>> data;
public:
    Dictionary() { data.reserve(1000); }

    V get(K key) {
        for (auto& entry : data) {
            if (entry.key == key) {
                return entry.value;
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

template<typename K>
class Dictionary<K, int> {
    vector<Entry<K, int>> data;
public:
    Dictionary() { data.reserve(1000); }

    int get(K key) {
        for (auto& entry : data) {
            if (entry.key == key) {
                return abs(entry.value);
            }
        }
    }

    void put(K key, int value) {
        data.emplace_back(Entry<K, int>(key, value));
    }

    int remove(K key) {
        for (auto it = data.begin(); it != data.end(); it++) {
            if (it->key == key) {
                int value = it->value;
                data.erase(it);
                return value;
            }
        }
    }

    int size() {
        int sum = 0;
        for (auto& entry : data) {
            sum += abs(entry.value);
        }
        return sum;
    }
};

int main() {
    Dictionary<string, int> dict;
    dict.put("a", -1);
    dict.put("b", 2);
    dict.put("c", -3);
    dict.put("d", 4);
    dict.put("e", 5);
    dict.put("f", -6);
    dict.put("g", 7);
    dict.remove("a");
    dict.remove("b");
    dict.remove("c");

    cout << dict.size() << endl;
    cout << dict.get("e") << endl;

    return 0;
}
