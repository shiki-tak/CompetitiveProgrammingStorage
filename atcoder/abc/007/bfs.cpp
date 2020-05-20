#include <iostream>
#include <queue>

using namespace std;

typedef pair<int, int> point_t;

char maze[51][51];

bool is_in_field(int col, int row, const point_t &point)
{
    const int c = point.second;
    const int r = point.first;
    return (0 <= c && c < col) && (0 <= r && r < row); 
}

int bfs(int col, int row, const point_t &start, const point_t &goal)
{
    // マスの訪問履歴
    int visit[51][51] = {0};

    // 移動方向
    const point_t points[] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    queue<point_t> que;

    // 探索開始
    que.push(start);

    while(!que.empty()) {

        // queueの先頭から一マス取得する
        point_t current_position = que.front(); que.pop();

        // 取り出したマスがゴールなら探索終了
        if (current_position == goal) {
            return visit[current_position.first][current_position.second];
        }

        for (auto &point : points) {
            // 取り出したマス（current_position）から上下左右のいずれかに移動する
            point_t next_position; //  = current_position + point;
            next_position.first = current_position.first + point.first;
            next_position.second = current_position.second + point.second;

            // 移動先のマス（next_position）が迷路外でないことを確認する
            if (is_in_field(col, row, next_position)) {
                char c = maze[next_position.first][next_position.second];

                // 移動先のマス（next_position）が道またはゴールであることを確認する
                if (c == '.' || next_position == goal) {
                    if (visit[next_position.first][next_position.second] == 0) {
                        que.push(next_position);
                        visit[next_position.first][next_position.second] = visit[current_position.first][current_position.second] + 1;
                    }
                }
            }
        }
    }
    return -1;
}

int main()
{
    int r, c; cin >> r >> c;
    point_t start, goal;
    int sy, sx; cin >> sy >> sx;
    int gy, gx; cin >> gy >> gx;

    start.first = sy;
    start.second = sx;

    goal.first = gy;
    goal.second = gx;

    for (int i = 1; i <= r; i++) {
        for (int j = 1; j <= c; j++) {
            cin >> maze[i][j];
        }
    }

    cout << bfs(c, r, start, goal) << endl;

    return 0;
}