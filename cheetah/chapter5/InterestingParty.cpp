#include <iostream>
#include <string>
#include <cmath>
#include <map>

using namespace std;

// input:
// 4
// fishing gardening swimming fishing
// hunting fishing fishing biting
// return: 4
//
// input:
// 4
// variety diversity loquacity courtesy
// talking speaking discussion meeting
// return: 1
//
// input:
// 4
// snakes programming cobra monty
// python python anaconda python
// return: 3

int main()
{
  int n; cin >> n;
  string first[n];
  string second[n];

  map<string, int> interestingMap;

  for (int i = 0; i < n; i++) {
    cin >> first[i];
    interestingMap[first[i]] = 0;
  }
  for (int i = 0; i < n; i++) {
    cin >> second[i];
    interestingMap[second[i]] = 0;
  }

  // それぞれの興味について加算していく
  for (string x : first) interestingMap[x]++;
  for (string x : second) interestingMap[x]++;

  // mapの中で最大値を見つけてansにする
  int ans;
  for (auto &x : interestingMap) ans = max(ans, x.second);

  cout << ans << endl;

  return 0;
}
