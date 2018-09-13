#include <iostream>

using namespace std;

void buildHeap(int a[], int n) {
    int aLen = n - 1;
    while(true) {
        int i = (aLen - 1) / 2;
        if (aLen < 0 || a[aLen] <= a[i]) break;
        int tmp = a[aLen];
        a[aLen] = a[i];
        a[i] = tmp;
        aLen = i;
    }
}

void swap() {

}

int main()
{
    int n; cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for (int i = n; i > 0; i--) {
        buildHeap(a, i);
    }
    for (int i = 0; i < n; i++) {
        if (i == n - 1) {
            cout << a[i] << endl;
        } else {
            cout << a[i] << " ";
        }
    }

    return 0;
}