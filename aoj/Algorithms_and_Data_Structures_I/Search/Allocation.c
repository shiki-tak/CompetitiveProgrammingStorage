#include <stdio.h>

int check_all(int mid, int k, int n, int w[]) {
  int j = 0;
  for (int i = 0; i < k; i++) {
    int s = 0;
    while(s + w[j] <= mid) {
      s += w[j];
      j += 1;
      if (j == n) {
        return n;
      }
    }
  }
  return j;
}

int allocation(int n, int k, int w[]) {
  int right = 100000 * 10000;
  int left = 0;

  while(right - left > 1) {
    int mid = (right + left) / 2;
    int v = check_all(mid, k, n, w);
    if (v >= n) {
      right = mid;
    } else {
      left = mid;
    }
  }
  return right;
}

int main() {
  int n, k;
  scanf("%d%d", &n, &k);
  int w[n];
  for (int i = 0; i < n; i++) {
    scanf("%d", &w[i]);
  }

  printf("%d", allocation(n, k, w));
  
  return 0;
}
