#include <iostream>
#include <queue>

using namespace std;
typedef pair<int, int> P;

int h = 0, w = 0;
char maze[51][51];
int visited[51][51] = { 0 };
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int cnt = 0;

bool bfs(int x, int y)
{
    bool goal = false;
    queue<P> que;

    que.push(P(x, y));

    while (que.size()) {
        P p = que.front(); que.pop();

        if (p.first == h - 1 && p.second == w - 1) {
            goal = true;
            break;
        }

        for (int i = 0; i < 4; i++) {
            int nx = p.first + dx[i], ny = p.second + dy[i];

            if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                if (visited[nx][ny] == 1) continue;
                if (visited[nx][ny] == 0 && maze[nx][ny] == '.') {
                    que.push(P(nx, ny));
                    visited[nx][ny] = visited[p.first][p.second] + 1;
                }
            }
        }
    }

    if (goal) cnt = cnt - visited[h - 1][w - 1];
    else      cnt = -1;

    return goal;
}

int main()
{
    cin >> h >> w;

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> maze[i][j];
            if (maze[i][j] == '.') cnt++;
        }
    }

    cout << (bfs(0, 0) ? cnt - 1 : -1) << endl;     // スタート地点を除くため、cnt - 1

    return 0;
}