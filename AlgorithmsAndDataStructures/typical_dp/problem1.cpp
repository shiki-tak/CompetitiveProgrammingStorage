#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int n; cin >> n;
    int a[10010];
    int dp[10010] = {0};
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        dp[i + 1] = max(dp[i], dp[i] + a[i]);
    }

    cout << dp[n] << endl;
    
    return 0;
}