#include <stdio.h>

int check_number(int target, int a[], int start, int end) {
  int is_match = 0;

  while(start <= end) {
    int middle = (start + end) / 2;

    if (target == a[middle]) {
      is_match = 1;
      break;
    } else if (a[middle] < target) {
      start = middle + 1;
    } else {
      end = middle - 1;
    }
  }
  return is_match;
}

int binary(int a[], int b[], int n, int m) {
  int cnt = 0;

  for (int i = 0; i < m; i++) {
    if(check_number(b[i], a, 0, n-1)) {
      cnt++;
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
  printf("%d\n", binary(a, b, n, m));

  return 0;
}
