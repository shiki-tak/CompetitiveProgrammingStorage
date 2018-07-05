#include <iostream>
#include <cmath>
#include <vector>

using std::cin;
using std::cout;
using std::endl;
using std::pow;
using std::min;

typedef long long ll;

int main() {
  int n;
  long long cost = 10000000;

  cin >> n;

  long long a[n];

  for (int i = 0; i < n; i++) {
    int num;
    cin >> num;
    a[i] = num;
  }

  for (int i = -100; i < 101; i++) {
    long long t = 0;
    for (int j = 0; j < n; j++) {
      long long diff = a[j] - i;
      t += pow(diff, 2);
    }
    cost = min(cost, t);
  }
  cout << cost << endl;

  return 0;
}
