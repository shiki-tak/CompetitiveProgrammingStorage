#include <stdio.h>

void compare_profit(int r[], int n) {
  int max = r[1] - r[0];
  int min_r = r[0];

  for (int i = 1; i <= n - 1; i++) {
    if (r[i] - min_r > max) {
      max = r[i] - min_r;
    }
    if (min_r > r[i]) {
      min_r = r[i];
    }
  }
  printf("%d\n", max);
}

int main() {
  int n;
  scanf("%d", &n);
  int r[n];
  for (int i = 0; i < n; i++) {
    scanf("%d", &r[i]);
  }
  compare_profit(r, n);

  return 0;
}
