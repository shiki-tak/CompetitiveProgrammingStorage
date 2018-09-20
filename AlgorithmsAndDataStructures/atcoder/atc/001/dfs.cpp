#include <iostream>

using namespace std;

char citySection[501][501];
int h = 0, w = 0;
int sX = 0, sY = 0;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0}; 

bool dfs(int x, int y, int ans) {
    citySection[x][y] = 'E';

    for (int i = 0; i < 4; i++) {
        if (x + dx[i] >= 0 && x + dx[i] < h && y + dy[i] >= 0 && y + dy[i] < w) {
            if (citySection[x + dx[i]][y + dy[i]] == '.') {
                ans = dfs(x + dx[i], y + dy[i], ans);
            } else if (citySection[x + dx[i]][y + dy[i]] == 'g') {
                ans = true;
                return ans;
            }
        }
    }

    return ans;
}

int main()
{
    cin >> h >> w;

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> citySection[i][j];
            if (citySection[i][j] == 's') { sX = i; sY = j; }
        }
    }
    cout << (dfs(sX, sY, false) ? "Yes" : "No") << endl;

    return 0;
}