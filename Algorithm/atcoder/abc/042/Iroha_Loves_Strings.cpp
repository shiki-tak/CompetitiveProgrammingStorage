#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using std::cin;
using std::cout;
using std::endl;
using std::string;
using std::vector;
using std::sort;

int main() {
  int n, l;
  vector<string> v;

  cin >> n >> l;

  for (int i = 0; i < n; i++) {
    string s;
    cin >> s;
    v.push_back(s);
  }

  sort(v.begin(), v.end());
  for (string x : v) cout << x;
  cout << endl;

  return 0;
}
