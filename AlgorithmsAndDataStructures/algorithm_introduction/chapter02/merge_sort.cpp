#include <iostream>

using namespace std;

void sort(int a[], int result[], int start, int end) {
    if (end - start < 2) {
        return ;
    }
    if (end - start == 2) {
        if (result[start] > result[start + 1]) {
            // 入れ替え
            int tmp = result[start];
            result[start] = result[start + 1];
            result[start + 1] = tmp;
        }
        return ;
    }
    int mid = (start + end) / 2;
    sort(result, a, start, mid);
    sort(result, a, mid, end);

    int i = start;
    int j = mid;
    int idx = start;
    while (idx < end) {
        if (j >= end || (i < mid && a[i] < a[j])) {
            result[idx] = a[i];
            i++;
        } else {
            result[idx] = a[j];
            j++;
        }
        idx++;
    }
    for (int i = 0; i < end; i++) {
        if (i == end - 1) cout << result[i] << endl;
        else              cout << result[i] << " ";
    }
}

void mergeSort(int a[], int n) {
    int copy[n + 1];
    for (int i = 0; i < n; i++) {
        copy[i] = a[i];
    }
    sort(copy, a, 0, n);
}

int main()
{
    int n; cin >> n;
    int a[n + 1];
    for (int i = 0; i < n; i++) cin >> a[i];

    mergeSort(a, n);

    return 0;
}