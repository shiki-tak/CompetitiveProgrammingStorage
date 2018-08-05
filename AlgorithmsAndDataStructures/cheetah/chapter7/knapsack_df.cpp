#include <iostream>
#include <cmath>

using namespace std;


int main()
{
    int ws[5] = {3, 4, 1, 2, 3};
    int ps[5] = {2, 3, 2, 3, 6};
    int maxw = 10;
    // dp[x][y] ... x: x番目の価値, y: 重さ
    int dp[6][11];
    int ret = 0;

    // 初期化
    for (int i = 0; i <= maxw; i++) dp[0][i] = 0;

    for (int i = 0; i < 5; i++) {
        for (int j = 0; j <= maxw; j++) {
            if (j >= ws[i]) {
                dp[i + 1][j] = max(dp[i][j - ws[i]] + ps[i], dp[i][j]);
            } else {
                dp[i + 1][j] = dp[i][j];
            }
        }
    }

    cout << dp[5][10] << endl;
    return 0;
}