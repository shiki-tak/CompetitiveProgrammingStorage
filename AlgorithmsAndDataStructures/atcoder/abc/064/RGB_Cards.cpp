#include <iostream>

using namespace std;
int main()
{
  int r, g, b;
  cin >> r >> g >> b;
  if ((100 * r + 10 * g + 1 * b) % 4 == 0) cout << "YES" << endl;
  else                                     cout << "NO" << endl;
  return 0;
}
