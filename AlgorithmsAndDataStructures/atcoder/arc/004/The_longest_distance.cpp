#include <iostream>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cstdlib>

using namespace std;

int main()
{
  int n; cin >> n;
  float d_max;
  int xi[n], yi[n];

  // 入力
  for (int i = 0; i < n; i++) {
    cin >> xi[i] >> yi[i];
    if (i != 0) {
      for (int j = 0; j < i; j++) {
        float d = sqrt((xi[i] - xi[j]) *  (xi[i] - xi[j]) + (yi[i] - yi[j]) * (yi[i] - yi[j]));
        if (d > d_max) d_max = d;
      }
    }
  }

  cout << d_max << endl;

  return 0;
}
