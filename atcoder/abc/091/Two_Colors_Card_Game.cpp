#include <iostream>
#include <string>
#include <map>

using namespace std;

int main()
{
  int n, m;

  cin >> n;
  string blue_cards[n];

  for (int i = 0; i < n; i++) cin >> blue_cards[i];

  cin >> m;
  string red_cards[m];

  for (int i = 0; i < m; i++) cin >> red_cards[i];

  map<string,int> ans;

  for (string x : blue_cards) ans[x] += 1;
  for (string x : red_cards) ans[x] -= 1;

  int max = 0;
  for (auto &x : ans) if (x.second > max) max = x.second;

  cout << max << endl;

  return 0;
}
