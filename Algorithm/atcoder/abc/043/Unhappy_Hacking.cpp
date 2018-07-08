#include <iostream>
#include <string>

using std::cin;
using std::cout;
using std::endl;
using std::string;

int main() {
  string s;
  string res;

  cin >> s;
  for (char x : s) {
    if (x == '0') res.push_back('0');
    else if (x == '1') res.push_back('1');
    else if (x == 'B' && res != "") res.pop_back();
  }
  cout << res << endl;

  return 0;

}
