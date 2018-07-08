# include <stdio.h>

void bubbleSort(int a[], int n) {
  int cnt = 0;
  int s = 0;

  for (int i = 0; i < n - 1; i++) {
    for(int j = n - 1; j > i; j--) {
      if (a[j - 1] > a[j]) {
        // swap
        s = a[j - 1];
        a[j - 1] = a[j];
        a[j] = s;
        cnt++;
      }
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
  bubbleSort(a, n);

  return 0;
}
