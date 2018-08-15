#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int n; cin >> n;
    int maxW; cin >> maxW;
    int weight[110], value[110], dp[110][10010];
    for (int i = 0; i < n; i++) cin >> weight[i] >> value[i];

    // dpの初期化
    for (int w = 0; w <= maxW; w++) dp[0][w] = 0;

    for (int i = 0; i < n; i++) {
        for (int w = 0; w <= maxW; w++) {
            if (w >= weight[i]) dp[i + 1][w] = max(dp[i][w - weight[i]] + value[i], dp[i][w]);
            else                dp[i + 1][w] = dp[i][w];
        }
    }

    cout << dp[n][maxW] << endl;
    return 0;
}