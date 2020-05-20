#include <iostream>
#include <cmath>

using std::cin;
using std::cout;
using std::endl;
using std::max;

// dp[j][k][ka] が求めたいパターンの総和
int dp[101][101][2501];

int main()
{
  int n, a, maxX;
  long long sum;
  cin >> n >> a;
  int x[n];

  for (int i = 1; i <= n; i++) {
    cin >> x[i];
  }

  // maxX = max(for (int i = 1; i <= n; i++) x[i], a)
  for (int i = 1; i <= n; i++) {
    if (maxX < x[i]) maxX = x[i];
  }

  for (int j = 0; j <= n; j++) {
    for (int k = 0; k <= n; k++) {
      for (int s = 0; s <= n * maxX; s++) {
        if (1 <= j && x[j] > s) dp[j][k][s] = dp[j - 1][k][s];
        else if (1 <= j && 1 <= k && x[j] <= s) dp[j][k][s] = dp[j - 1][k][s] + dp[j - 1][k - 1][s - x[j]];
        else if (j == 0 && k == 0 && s == 0) dp[j][k][s] = 1;
        else dp[j][k][s] = 0;
      }
    }
  }

  for (int i = 1; i <= n; i++) {
    sum += dp[n][i][i * a];
  }

  cout << sum << endl;
}
