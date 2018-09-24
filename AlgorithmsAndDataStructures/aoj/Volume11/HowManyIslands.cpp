#include <iostream>

using namespace std;

int w = 0, h = 0;
int c[51][51];
int dx[8] = {0, 1, 1,  1,  0, -1, -1, -1};
int dy[8] = {1, 1, 0, -1, -1, -1,  0,  1}; 

void dfs(int x, int y) {
    c[x][y] = 2;    // 探索済みにする
    for (int i = 0; i < 8; i++) {
        if (x + dx[i] >= 0 && x + dx[i] < w && y + dy[i] >= 0 && y + dy[i] < h && c[x + dx[i]][y + dy[i]] == 1) {
            dfs(x + dx[i], y + dy[i]);
        }
    }

}

int main()
{
    int ans = 0;
    cin >> w >> h;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> c[i][j];
        }
    }

    for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
            if (c[i][j] == 1) {
                dfs(i, j);
                ans++;
            }
        }
    }

    cout << ans << endl;

    return 0;
}