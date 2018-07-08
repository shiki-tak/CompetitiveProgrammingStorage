#include <stdio.h>
#include <math.h>

int check_prime(int a[], int n) {
  int cnt = 0;

  for (int i = 0; i < n; i++) {
    int flag = 1;
    for (int j = 2; j <= sqrt(a[i]); j++) {
      if (a[i] % j == 0) {
        flag = 0;
        break;
      }
    }
    if (flag) {
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
  printf("%d\n", check_prime(a, n));
  return 0;
}
