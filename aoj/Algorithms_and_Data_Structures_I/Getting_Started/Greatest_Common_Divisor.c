#include <stdio.h>

void gcd(int max, int min) {
  int w = 1;
  while (w) {
    w = max % min;
    if (w) {
      max = min;
      min = w;
    }
  }
  printf("%d\n", min);
}

int main() {
  int x, y, z;

  scanf("%d %d", &x, &y);
  if (x > y) {
    gcd(x, y);
  } else if (x < y) {
    gcd(y, x);
  } else {
    printf("%d\n", x);
  }
  return 0;
}
