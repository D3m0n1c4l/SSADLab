#include <iostream>
#include <vector>
using namespace std;

int jan22task3() {
    int n;
    cin >> n;
    vector<int> v;
    for (int i = 0; i < n; i++) {
        int element;
        cin >> element;
        v.push_back(element);
    }
    for (int i = 0; i < v.size(); i++) {
        for (int j = 0; j < v.size(); j++) {
            if (v[j] == v[i] && i != j) {
                v.erase(v.begin() + j);
            }
        }
    }
    for (const int i : v) {
        cout << i << " ";
    }

    return 0;
}
