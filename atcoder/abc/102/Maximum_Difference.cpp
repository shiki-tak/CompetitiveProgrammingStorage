#include <iostream>
#include <climits>

using namespace std;

int main()
{
  int n; cin >> n;
  int min = INT_MAX;
  int max = 1;

  for (int i = 0; i < n; i++) {
    int a; cin >> a;
    if (max < a) max = a;
    if (min > a) min = a;
  }

  cout << max - min << endl;

  return 0;
}
