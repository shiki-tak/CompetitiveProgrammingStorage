#include <iostream>

using namespace std;

void selectionSort(int a[], int n) {

    for (int j = n; j > 0; j--) {
        bool change = false;
        int maxP = j;
        for (int i = 0; i <= j - 1; i++) {
            if (a[i] > a[maxP]) {
                maxP = i;
                change = true;
            }
        }
        if (change) {
            int tmp = a[maxP];
            a[maxP] = a[j];
            a[j] = tmp;
        }
    }

    for (int i = 0; i < n; i++) {
        if (i == n - 1) cout << a[i] << endl;
        else cout << a[i] << " ";
    }
}

int main()
{
    int n; cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) cin >> a[i];

    selectionSort(a, n);

    return 0;
}