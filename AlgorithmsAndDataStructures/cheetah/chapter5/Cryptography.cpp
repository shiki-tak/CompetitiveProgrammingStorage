#include <iostream>
#include <cmath>

using namespace std;

typedef long long ll;
// input: 1 2 3
// return: 12
//
// input: 1 3 2 1 1 3
// return: 36
//
// input: 1000 999 998 997 996 995
// return: 986074810223904000

int main()
{
  int n; cin >> n;
  int numbers[n];

  for (int i = 0; i < n; i++) cin >> numbers[i];

  ll max_product = 1;

  for (int i = 0; i < n; i++) {
    ll product = 1;
    // 積を計算
    for (int j = 0; j < n; j++) {
      if (i == j) product *= (numbers[j] + 1);
      else        product *= numbers[j];
    }
    max_product = max(max_product, product);
  }

  cout << max_product << endl;

  return 0;
}
