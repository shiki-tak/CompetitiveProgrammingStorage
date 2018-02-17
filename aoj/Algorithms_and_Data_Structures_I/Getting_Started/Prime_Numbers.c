#include <stdio.h>
#include <math.h>

// int check_prime(int a[], int n) {
//   int cnt = 0;
//
//   for (int i = 0; i < a[i]; i++) {
//     int flag = 1;
//     for (int j = 2; j <= sqrt(a[i]); j++) {
//       if (a[i] % j == 0) {
//         flag = 0;
//         break;
//       }
//     }
//     if (flag) {
//       cnt++;
//     }
//   }
//   return cnt;
// }
//
// int main() {
//   int n;
//   scanf("%d", &n);
//   int a[n];
//   for (int i = 0; i < n; i++) {
//     scanf("%d", &a[i]);
//   }
//   printf("%d\n", check_prime(a, n));
//   return 0;
// }

int check_prime(int target) {
  int flag = 1;

  for (int i = 2; i <= sqrt(target); i++) {
    if (target % i == 0) {
      flag = 0;
      break;
    }
  }
  return flag;
}

int main() {
  int n = 0;
  int cnt = 0;
  scanf("%d\n", &n);
  int a[n];
  for (int i = 0; i < n; i++) {
    scanf("%d\n", &a[i]);
  }

  for (int i = 0; i < n; i++) {
    if (check_prime(a[i])) {
      cnt++;
    }
  }
  printf("%d\n", cnt);
  return 0;
}
