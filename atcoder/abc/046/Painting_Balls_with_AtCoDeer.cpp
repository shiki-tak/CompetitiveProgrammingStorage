#include <iostream>
#include <cmath>

using std::cin;
using std::cout;
using std::endl;
using std::pow;

int main()
{
  int n, k;

  cin >> n >> k;
  // 問題文の条件からボールを塗る場合の数を計算するための式
  long long ans = pow((k - 1), n - 1) * k;

  cout << ans << endl;

  return 0;
}
