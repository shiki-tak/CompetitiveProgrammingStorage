#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int t[101], ans[101];

int main()
{
  int n, m, sumInit, x, p;

  cin >> n;
  for (int i = 1; i <= n; i++) {
    cin >> t[i];
    // ドリンクを飲まない場合のトータル時間を計算しておく
    sumInit += t[i];
  }

  cin >> m;
  for (int i = 1; i <= m; i++) {
    cin >> x >> p;
    ans[i] = sumInit + p - t[x];
  }
  for (int i = 1; i <= m; i++) cout << ans[i] << endl;

  return 0;
}
