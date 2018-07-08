#include <stdio.h>
int n, a[50];

int rec(int i, int b) {
  int res = 0;
  if (b == 0) {
    res = 1;
    return res;
  }
  if (i >= n) {
    return res;
  }
  return rec(i + 1, b) || rec(i + 1, b - a[i]);
}

int main() {
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  int m;
  scanf("%d", &m);
  int b[m];
  for (int i = 0; i < m; i++) {
    scanf("%d", &b[i]);
  }

  for (int i = 0; i < m; i++) {
    if (rec(0, b[i])) {
      printf("%s\n", "yes");
    } else {
      printf("%s\n", "no");
    }
  }
  return 0;
}
