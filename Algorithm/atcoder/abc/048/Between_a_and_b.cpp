#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main() {
  long long a, b, x, ans;

  cin >> a >> b >> x;

  ans = (b - a + 1) / x;

  if (b % x == 0) ans++;

  cout << ans << endl;

  return 0;
}
