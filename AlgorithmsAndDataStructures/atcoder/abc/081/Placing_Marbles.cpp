#include <iostream>
#include <string>

using namespace std;

int main()
{
  string x;
  int cnt;
  cin >> x;
  for (int i = 0; i < 3; i++) if (x[i] == '1') cnt++;
  cout << cnt << endl;

  return 0;
}
