#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int a[100];
    for (int i = 0; i < n; i++) cin >> a[i];

    for (int j = 1; j < n; j++) {
        int key = a[j];
        int i = j - 1;
        while (i >= 0 && a[i] > key) {
            a[i + 1] = a[i];
            i--;
        }
        a[i + 1] = key;

        for (int k = 0; k < n; k++) {
            if (k != n - 1) cout << a[k] << " ";
            else            cout << a[k] << endl;
        }
    }
    return 0;
}