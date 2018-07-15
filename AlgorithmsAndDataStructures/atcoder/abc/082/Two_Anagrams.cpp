#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main()
{
  vector<char> s;
  vector<char> t;

  string a, b;
  cin >> a;
  cin >> b;

  for (char x : a) s.push_back(x);
  for (char x : b) t.push_back(x);

  // 昇順
  sort(s.begin(), s.end());
  // 降順
  sort(t.begin(), t.end(), greater<char>());

  // 判定
  bool ans = false;
  for (int i = 0; i < b.length(); i++) {
    if (s[i] < t[i]) {
      ans = true;
      break;
    } else if (s[i] > t[i]) {
      break;
    }
  }

  if (ans) cout << "Yes" << endl;
  else     cout << "No" << endl;

  return 0;
}
