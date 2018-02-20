#include <stdio.h>

void selectionSort(int a[], int n) {
  int cnt = 0;
  int s = 0;
  int min_j = 0;

  for (int i = 0; i < n; i++) {
    int min_j = i;
    int flag = 0;
    for (int j = i + 1; j < n; j++) {
      if (a[min_j] > a[j]) {
        min_j = j;
        flag = 1;
      } else {
        continue;
      }
    }
    if (flag) {
      s = a[i];
      a[i] = a[min_j];
      a[min_j] = s;
      cnt++;
    }
  }
  for (int i = 0; i < n; i++) {
    if (i == n - 1) {
      printf("%d", a[n - 1]);
    } else {
      printf("%d ", a[i]);
    }
  }
  printf("\n");
  printf("%d\n", cnt);
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
