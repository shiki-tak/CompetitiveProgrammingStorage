#include <iostream>

using namespace std;

char s[51][51];

int vx[] = { 0,  1, 1, 1, 0, -1, -1, -1};
int vy[] = {-1, -1, 0, 1, 1,  1,  0, -1};

// 周囲にMineがいくつあるかカウントする
int countMine(int h, int w, int x, int y) {
    int cnt = 0;

    for (int i = 0; i < 8; i++) {
        if (((x + vx[i] < w && x + vx[i] >= 0) || (y + vy[i] < h && y + vy[i] >= 0)) && (s[x + vx[i]][y + vy[i]] == '#')) cnt++;
    }

    return cnt;

}
int main()
{
    int h, w; cin >> h >> w;

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> s[i][j];
        }
    }

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            if (s[i][j] == '.') s[i][j] += 2 + countMine(h, w, i, j);

            if (j != w - 1) cout << s[i][j];
            else cout << s[i][j] << endl;
        }
    }
    
    return 0;
}
