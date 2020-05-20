#include <iostream>
#include <string>

using namespace std;

int main()
{
  string s;
  cin >> s;
  int ramenPrice = 700;

  for (char c : s) {
    if (c == 'o') ramenPrice += 100;
  }
  cout << ramenPrice << endl;

  return 0;
}
