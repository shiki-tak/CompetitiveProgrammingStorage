#include <iostream>

using namespace std;

int checkEven(int a[], int n) {
  int res = 1;
  for (int i = 0; i < n; i++) {
    if (a[i] % 2 != 0) {
      res = 0;
      break;
    }
  }
  return res;
}

int main()
{
  int n, ans;

  cin >> n;
  int a[n];

  for (int i = 0; i < n; i++) cin >> a[i];

  while (true) {
    // 全ての要素が偶数のとき
    if (checkEven(a, n) == 1) {
      ans++;
      for (int i = 0; i < n; i++) a[i] = a[i] / 2;
    } else {
      break;
    }
  }

  cout << ans << endl;
  return 0;
}
