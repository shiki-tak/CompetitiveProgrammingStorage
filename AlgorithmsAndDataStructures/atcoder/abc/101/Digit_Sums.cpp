#include <iostream>

using namespace std;

int main()
{
  int n; cin >> n;
  int tmp = 0;
  int m = n;

  while(n) {
    tmp += n % 10;
    n /= 10;
  }

  if (m % tmp == 0) cout << "Yes" << endl;
  else              cout << "No" << endl;

  return 0;
}
