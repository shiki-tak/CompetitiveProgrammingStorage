#include <stdio.h>

void selectionSort(int a[], int n) {
  int count;
  int s;
  for (int i = 0; i < n - 1; i++) {
    int min = a[i + 1];
    for (int j = n - 1; j < i; j--) {
      if (min > a[j]) {
        min_j = j;
      }
    }
    s = a[i];
    a[i] = a[min_j];
    a[min_j] = s;
  }
}


int main() {
  int n;
  scanf("%d", &n);
  int a[n];
  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  selectionSort(a, n);

  return 0;
}
