#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main() {
  int n, sum;
  cin >> n;

  for (int i = 1; i <= n; i++) sum = sum + i;
  cout << sum << endl;

  return 0;
}
