#include <stdio.h>

int linear(int a[], int b[], int n, int m) {
  int cnt = 0;
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (b[i] == a[j]){
        cnt++;
        break;
      }
    }
  }
  return cnt;
}

int main() {
  int n;
  scanf("%d", &n);
  int a[n];
  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  int m;
  scanf("%d", &m);
  int b[m];
  for (int i = 0; i < m; i++) {
    scanf("%d", &b[i]);
  }
  printf("%d\n", linear(a, b, n, m));
  return 0;
}
