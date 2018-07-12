#include <stdio.h>
#define MAX 101

int main() {

  int n, u, k, v;
  int A[MAX][MAX];

  scanf("%d", &n);

  // 行列の初期化
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      A[i][j] = 0;
    }
  }

  // 入力
  for (int i = 1; i <= n; i++) {
    scanf("%d %d", &u, &k);
    for (int j = 0; j < k; j++) {
      scanf("%d", &v);
      A[u][v] = 1;
    }
  }

  // 出力
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (j == n) {
        printf("%d", A[i][j]);
      } else {
        printf("%d ", A[i][j]);
      }
    }
    printf("\n");
  }

  return 0;
}
