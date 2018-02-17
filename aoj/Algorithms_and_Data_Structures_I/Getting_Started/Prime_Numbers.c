#include <stdio.h>
#include <math.h>

void check_prime(int a[], int n) {
  int count;

  for (int i = 0; i < a[i]; i++) {
    int flag = 1;
    for (int j = 2; j <= sqrt(a[i]); j++) {
      if (a[i] % j == 0) {
        flag = 0;
        break;
      }
    }
    if (flag) {
      count++;
    }
  }
  printf("%d", count);
}

int main() {
  int n;
  scanf("%d", &n);
  int a[n];
  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  check_prime(a, n);
  return 0;
}
