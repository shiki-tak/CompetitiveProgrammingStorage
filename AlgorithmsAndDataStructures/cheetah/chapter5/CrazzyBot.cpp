#include <iostream>

using namespace std;

int board[29][29] = {0};
int vx[4] = {1, -1, 0, 0};
int vy[4] = {0, 0, 1, -1};

long double probability[4];

long double dfs(int x, int y, int n) {
    long double ret = 0;

    // すでに通過したことがあれば確率は0
    if (board[x][y] == 1) return 0;
    // ロボットが初期位置から動かなければ確率は1
    if (n == 0)           return 1;

    // ロボットが移動した点の値を1にして、すでに通過したことがあるこにする
    board[x][y] = 1;

　  // search
    for (int i = 0; i < 4; i++) ret += dfs(x + vx[i], y + vy[i], n - 1) * probability[i];

    // 探索が完了したらボードを初期設定に戻す
    board[x][y] = 0;

    return ret;
}

int main()
{
    int n; cin >> n;
    int east, west, south, north; cin >> east >> west >> south >> north;
    long double ans = 0.0;

    // 確率の計算
    probability[0] = east / 100.0;
    probability[1] = west / 100.0;
    probability[2] = south / 100.0;
    probability[3] = north / 100.0;

    // ロボット起動
    ans = dfs(14, 14, n);

    cout << ans << endl;

    return 0;
}