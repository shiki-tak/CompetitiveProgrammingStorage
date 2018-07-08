#include <stdio.h>

void countingSort(int a[], int c[], int n) {
  int b[2000001];
  for (int i = n; 0 < i; i--) {
    b[c[a[i]]] = a[i];
    c[a[i]]--;
  }
  for (int i = 1; i <= n; i++) {
    if (i == n) {
      printf("%d\n", b[i]);
    } else {
      printf("%d ", b[i]);
    }
  }
}

int main() {
  int n;
  int a[2000001];

  int c[10000];
  int c2[10000];
  int a_max = 0;

  scanf("%d", &n);
  for (int i = 1; i <= n; i++) {
    scanf("%d", &a[i]);
    if (a_max < a[i]) {
      a_max = a[i];
    }
    c2[a[i]]++;
  }
  // Cの累積和を求める
  for (int i = 0; i <= a_max; i++) {
    for (int j = 0; j <= i; j++) {
      c[i] += c2[j];
    }
  }
  countingSort(a, c, n);
  return 0;
}
