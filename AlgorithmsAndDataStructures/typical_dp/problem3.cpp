#include <iostream>

using namespace std;

int main()
{
    int n, target; cin >> n >> target;
    int a[110];
    for (int i = 0; i < n; i++) cin >> a[i];
    bool dp[110][10010];

    // dpテーブルの初期化
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == 0 && j == 0) dp[i][j] = true;
            else                  dp[i][j] = false;
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= target; j++) {
            dp[i + 1][j] |= dp[i][j];
            if (j >= a[i]) dp[i + 1][j] |= dp[i][j - a[i]];
        }
    }

    cout << (dp[n][target] ? "Yes" : "No") << endl;

    return 0;
}