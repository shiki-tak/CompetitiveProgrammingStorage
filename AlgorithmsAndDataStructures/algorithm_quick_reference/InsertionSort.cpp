#include <iostream>

using namespace std;

void insertSort(int a[], int n) {
    for (int i = 1; i < n; i++) {
        int v = a[i];
        int j = i - 1;
        while (j >= 0 && a[j] > v) {
            a[j + 1] = a[j];
            j--;
            a[j + 1] = v;
        }
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                cout << a[i] << endl;
            } else {
                cout << a[i] << " ";
            }
        }
    }
}

int main() {
    int n; cin >> n;
    int a[n + 1];
    for (int i = 0; i < n; i++) cin >> a[i];
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                cout << a[i] << endl;
            } else {
                cout << a[i] << " ";
            }
        }

    insertSort(a, n);


    return 0;
}