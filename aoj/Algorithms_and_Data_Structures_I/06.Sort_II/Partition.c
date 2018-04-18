#include <stdio.h>

void swap(int *x, int *y) {
  int tmp = *x;
  *x = *y;
  *y = tmp;
}

void partition(int a[], int p, int r) {
  int i = p - 1;
  for (int j = p; j <= r - 1; j++) {
    if (a[j] <= a[r]) {
      i++;
      swap(&a[i], &a[j]);
    }
  }
  swap(&a[i+1], &a[r]);

  for (int k = 0; k <= r; k++) {
    if (k == i + 1) {
      printf("[%d]", a[k]);
    } else {
      printf("%d", a[k]);
    }
    if (k == r) {
      printf("\n");
    } else {
      printf(" ");
    }
  }
}
int main() {
  int n;
  scanf("%d", &n);
  int a[n];
  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  partition(a, 0, n - 1);
  return 0;
}
