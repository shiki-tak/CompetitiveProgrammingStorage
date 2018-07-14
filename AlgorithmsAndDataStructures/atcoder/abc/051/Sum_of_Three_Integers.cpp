#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main() {
  int k, s, ans;
  cin >> k >> s;

  for (int i = 0; i <= k; i++) { // x = i
    // condition: s - i = y + z (0 <= y, z <= k)
    for (int j = 0; j <= k; j++) if (s - (i + j) <= k && s - (i + j) >= 0) ans++;
  }
  cout << ans << endl;
  return 0;
}
