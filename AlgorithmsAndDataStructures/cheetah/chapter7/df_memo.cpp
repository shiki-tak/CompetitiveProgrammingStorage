#include <iostream>

using namespace std;

int main()
{
    const int h = 5, w = 4;
    int dp[h + 1][w + 1] = {0};

    for (int i = 0; i <= h; i++) {
        for (int j = 0; j <= w; j++) {
            if (i == 0 && j == 0) dp[i][j] = 1;
            if (i != 0) dp[i][j] += dp[i - 1][j];
            if (j != 0) dp[i][j] += dp[i][j - 1];
        }
    }
    cout << dp[h][w] << endl;

    return 0;
}