#include <iostream>
#include <queue>
#include <cmath>

using namespace std;

typedef pair<int, int> point_t;

char maze[51][51];
int moveRow[51];
int moveCol[51];

bool is_in_field(int col, int row, const point_t &point)
{
    const int c = point.second;
    const int r = point.first;
    return (0 <= c && c < col) && (0 <= r && r < row); 
}

int bfs(int col, int row, int n, const point_t &start)
{
    int ret = -1;

    // マスの訪問履歴
    int visit[51][51] = {0};

    // 移動方向
    point_t points[n];

    for (int i = 0; i < n; i++) {
        points[i].first = moveRow[i];
        points[i].second = moveCol[i];
    }

    queue<point_t> que;

    // 探索開始
    que.push(start);

    while(!que.empty()) {

        point_t cur = que.front(); que.pop();

        for (auto &point : points) {
            point_t next;
            next.first = cur.first + point.first;
            next.second = cur.second + point.second;

            if (is_in_field(col, row, next)) {
                char c = maze[next.first][next.second];

                if (c == '.') {
                    if (visit[next.first][next.second] == 0) {
                        que.push(next);
                        visit[next.first][next.second] = visit[cur.first][cur.second] + 1;
                        ret = max(ret, visit[next.first][next.second]);
                    }
                }
            }
        }
    }

    // cout << "max_leap: " << max_leap << endl;
    bool flag = true;

    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (visit[i][j] == 0 && maze[i][j] == '.' && (i != start.first && j != start.second)) {
                // 探索完了後にも訪問していないマスがあれば、ジムは迷路から抜け出せない
                flag = false;
                ret = -1;
                break;
            }
        }
        if (!flag) break;
    }

    return ret;
}

int main() 
{
    // c: 迷路のCol, r: 迷路のRow, n: moveRow, Colの数
    int r, c, n; cin >> r >> c >> n;

    // スタート位置の設定
    point_t start;
    int startRow, startCol; cin >> startRow >> startCol;
    start.first = startRow;
    start.second = startCol;

    // 迷路の作成
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            cin >> maze[i][j];
        }
    }
    // ジムが取り得る動き
    for (int i = 0; i < n; i++) cin >> moveRow[i] >> moveCol[i];

    cout << bfs(c, r, n, start) << endl;

    return 0;
}