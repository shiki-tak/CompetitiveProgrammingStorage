#include <iostream>
#include <cmath>
#include <climits>
#include <vector>

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

// numbersをvectorで保存して、sort
// minimum numberを+1して積を計算する
int main()
{
  int n; cin >> n;
  vector<int> numbers;

  for (int i = 0; i < n; i++) {
    int x; cin >> x;
    numbers.push_back(x);
  }

  // 昇順にsort
  sort(numbers.begin(), numbers.end());

  ll product = 1;

  for (int i = 0; i < n; i++) {
    if (i == 0) product *= (numbers[i] + 1);
    else        product *= numbers[i];
  }

  cout << product << endl;

  return 0;
}
