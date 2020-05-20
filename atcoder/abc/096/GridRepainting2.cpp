#include <iostream>

using namespace std;
int h = 0, w = 0;
char canvas[51][51] = { 0 };
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void dfs(int x, int y) {
    canvas[x][y] = 'B';

    for (int i = 0; i < 4; i++) {
        if (x + dx[i] >= 0 && x + dx[i] < h && y + dy[i] >= 0 && y + dy[i] < w && canvas[x + dx[i]][y + dy[i]] == '#') {
            dfs(x + dx[i], y + dy[i]);
        }
    }
}

bool checkAroundTarget(int x, int y) {
    int flag = false;
    for (int i = 0; i < 4; i++) {
        if (canvas[x + dx[i]][y + dy[i]] == '#') {
            flag = true;
            break;
        }
    }

    return flag;
}

int main()
{
    bool ans = true;
    cin >> h >> w;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> canvas[i][j];
        }
    }

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            if (canvas[i][j] == '#') {
                if (!checkAroundTarget(i, j)) {
                    ans = false;
                    break;
                }
                dfs(i, j);
            }
        }
        if (!ans) break;
    }

    cout << (ans ? "Yes" : "No") << endl;

    return 0;
}