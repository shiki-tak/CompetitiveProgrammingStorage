#include <iostream>
#include <queue>
#include <tuple>

using namespace std;

int h = 0, w = 0;
char maze[501][501];
int visited[501][501][3] = { 0 };
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

bool bfs(int sX, int sY) {
    bool ans = false;

    queue<tuple<int, int , int>> que;

    que.push(make_tuple(sX, sY, 0));

    while (que.size()) {
        int x, y, d;
        tie(x, y, d) = que.front(); que.pop();

        if (x == goal.first && y == goal.second) {
            ans = true;
            break;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                if (visited[nx][ny][d] == 1) continue;          // すでに探索済み
                if (maze[nx][ny] == '#' && d == 2) continue;    // 2回、器物損壊した
                if (maze[nx][ny] == '#' && d < 2) {
                    visited[nx][ny][d + 1] = 1;
                    que.push(make_tuple(nx, ny, d + 1)); 
                } else {
                    visited[nx][ny][d] = 1;
                    que.push(make_tuple(nx, ny, d));
                }
            }
        }
    }

    return ans;
}

int main()
{
    cin >> h >> w;
    int sX, sY = 0;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> maze[i][j];
            if (maze[i][j] == 's') { sX = i; sY = j; }
        }
    }

    cout << (bfs(sX, sY) ? "YES" : "NO") << endl;

    return 0;
}