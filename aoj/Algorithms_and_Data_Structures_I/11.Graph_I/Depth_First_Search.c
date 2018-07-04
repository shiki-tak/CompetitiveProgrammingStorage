#include <stdio.h>
#define N 101
#define WHITE 0
#define GRAY 1
#define BLACK 2

// 隣接行列
int M[N][N];

int color[N], d[N], f[N], tt;
int n, u, v, k;

// 再帰関数による深さ優先探索
void dfs_visit(int u) {
  color[u] = GRAY;
  d[u] = ++tt;  // 最初の訪問
  for (int i = 1; i <= n; i++) {
    if (M[u][i] == 0) continue;
    if (color[i] == WHITE) {
      dfs_visit(i);
    }
  }
  color[u] = BLACK;
  f[u] = ++tt;  // 訪問完了
}

void dfs() {

  // 初期化
  for (int i = 1; i <= n; i++) {
    color[i] = WHITE;
  }
  tt = 0;

  for (int i = 1; i <=n; i++) {
    if (color[i] == WHITE) {
      dfs_visit(i);
    }
  }

  for (int i = 1; i <= n; i++) {
    printf("%d %d %d\n", i, d[i], f[i]);
  }
}

int main() {
  scanf("%d", &n);

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      M[i][j] = 0;
    }
  }

  for (int i = 1; i <= n; i++) {
    scanf("%d %d", &u, &k);
    for (int j = 1; j <= k; j++) {
      scanf("%d", &v);
      M[u][v] = 1;
    }
  }

  dfs();

  return 0;
}
