#include <iostream>
#include <vector>

using namespace std;

int main()
{
  int n, k;

  cin >> n >> k;

  vector<int> iv;
  int ans = 0;

  // vectorの初期化
  for (int i = 0; i <= n; i++) iv.push_back(0);

  for (int i = 1; i <= n; i++) {
    int x; cin >> x;
    iv[x] += 1;
  }

  sort(iv.begin(), iv.end());

  // 0ではないivの要素の先頭のindexを探す
  int start;
  for (int i = 1; i <= n; i++) {
    if (iv[i] != 0) {
      start = i;
      break;
    }
  }

  if ((n - start + 1) > k) for (int i = start; i < n + 1 - k; i++) ans += iv[i];

  cout << ans << endl;

  return 0;
}
