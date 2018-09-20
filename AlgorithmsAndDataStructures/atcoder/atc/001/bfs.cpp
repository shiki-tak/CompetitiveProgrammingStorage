#include <iostream>

using namespace std;

char citySection[501][501];
int h = 0, w = 0;
int sX = 0, sY = 0;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0}; 

bool bfs(int x, int y, int ans) {
    if (citySection[x][y] == 'g') {
        ans = true;
        return ans;
    } else {
        citySection[x][y] = 'E';
    }

    for (int i = 0; i < 4; i++) {
        if ( x + dx[i] < 0 || x + dx[i] >= h) { continue; }
        if ( y + dy[i] < 0 || y + dy[i] >= w) { continue; }
        if (citySection[x + dx[i]][y + dy[i]] == '.' || citySection[x + dx[i]][y + dy[i]] == 'g') {

            ans = bfs(x + dx[i], y + dy[i], ans);
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
            if (citySection[i][j] == 's') {
                sX = i;
                sY = j;
            }
        }
    }
    cout << (bfs(sX, sY, false) ? "Yes" : "No") << endl;

    // for (int i = 0; i < h; i++) {
    //     for (int j = 0; j < w; j++) {
    //         if (j == w - 1) {
    //             cout << citySection[i][j] << endl;
    //         } else {
    //             cout << citySection[i][j];
    //         }
    //     }
    // }

    return 0;
}