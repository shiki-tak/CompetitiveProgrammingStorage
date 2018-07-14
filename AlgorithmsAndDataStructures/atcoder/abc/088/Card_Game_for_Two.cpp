#include <iostream>
#include <vector>

using namespace std;

int main(){
  int n;
  int alice = 0;
  int bob = 0;
  vector<int> a;

  cin >> n;
  for (int i = 0; i < n; i++) {
    int m;
    cin >> m;
    a.push_back(m);
  }
  sort(a.begin(), a.end(), greater<int>());

  for (int i = 0; i < n; i++) {
    if (i % 2 == 0) {
      alice += a[i];
    } else {
      bob += a[i];
    }
  }

  cout << alice - bob << endl;
  return 0;
}
