#include <iostream>
#include <string>

using namespace std;

int main()
{
  int n; cin >> n;
  string first[n];
  string second[n];

  for (int i = 0; i < n; i++) cin >> first[i];
  for (int i = 0; i < n; i++) cin >> second[i];

  // 誰も呼ばなければ1
  int friends = 1;

  for (int i = 0; i < n; i++) {
    int x = 1;
    int y = 1;
    for (int j = 0; j < n; j++) {
      if (i != j) {
        if (first[i] == first[j] || first[i] == second[j]) x++;
        if (second[i] == first[j] || second[i] == second[j]) y++;
      }
    }
    if (x > friends) friends = x;
    else if (y > friends) friends = y;
  }

  cout << friends << endl;

  return 0;
}
