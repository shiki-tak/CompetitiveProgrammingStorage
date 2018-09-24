#include <iostream>
#include <queue>

using namespace std;
typedef pair<int, int> P;

int n = 0, m = 0;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

char M[110][110];
int visited[110][110] = { 0 };

P start, goal;

int bfs(int sX, int sY) {
    queue<P> Que;

    Que.push(P(sX, sY));

    while (Que.size()) {
        P p = Que.front(); Que.pop();

        if (p.first == goal.first && p.second == goal.second) {
            break;
        }

        for (int i = 0; i < 4; i++) {
            int nx = p.first + dx[i], ny = p.second + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && (M[nx][ny] == '.' || M[nx][ny] == 'G') && visited[nx][ny] == 0) {
                Que.push(P(nx, ny));
                visited[nx][ny] = visited[p.first][p.second] + 1;
            }
        }
    }

    return visited[goal.first][goal.second];
}


int main()
{
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> M[i][j];
            if (M[i][j] == 'S') {
                start.first = i;
                start.second = j;
            }
            if (M[i][j] == 'G') {
                goal.first = i;
                goal.second = j;
            }
        }
    }
    cout << bfs(start.first, start.second) << endl;
    
    return 0;
}