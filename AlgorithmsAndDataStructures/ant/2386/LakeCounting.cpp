#include <iostream>

using namespace std;

char lc[110][110];
int n = 0;
int m = 0;

int dx[8] = {0, 1, 1, 1, 0, -1, -1, -1};
int dy[8] = {1, 1, 0, -1, -1, -1, 0, 1}; 

void dfs(int x, int y) {
    lc[x][y] = '.';
    for (int i = 0; i < 8; i++) {
        if (lc[x + dx[i]][y + dy[i]] == 'W' && x + dx[i] < n && y + dy[i] < m && x + dx[i] >= 0 && y + dy[i] >= 0) {
            dfs(x + dx[i], y + dy[i]);
        }
    }
}

int main()
{
    cin >> n >> m;
    int ans = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> lc[i][j];
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (lc[i][j] == 'W') {
                dfs(i, j);
                ans++;
            }
        }
    }

    cout << ans << endl;

    return 0;
}