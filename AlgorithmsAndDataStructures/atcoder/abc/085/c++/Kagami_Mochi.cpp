#include <iostream>
#include <vector>

using namespace std;

int main()
{
  int n;
  vector<int> a;

  cin >> n;
  for (int i = 0; i < n; i++) {
    int m;
    cin >> m;
    a.push_back(m);
  }
  sort(a.begin(), a.end());
  a.erase(unique(a.begin(), a.end()), a.end());

  cout << a.size() << endl;

  return 0;
}
