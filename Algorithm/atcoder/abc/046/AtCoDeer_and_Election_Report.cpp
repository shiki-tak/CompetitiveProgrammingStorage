#include <iostream>
#include <cmath>

using std::cin;
using std::cout;
using std::endl;
using std::min;

int sum;
int takahashiVotes[1001];
int aokiVotes[1001];

void numberOfVotes(int i, int t, int a)
{
  if (takahashiVotes[i - 1] <= t && aokiVotes[i - 1] <= a) {
    takahashiVotes[i] = t;
    aokiVotes[i] = a;
    sum = t + a;
  } else {
    if (takahashiVotes[i - 1] <= aokiVotes[i - 1]){
      for (int j = 2; ;j++) {
        if (takahashiVotes[i - 1] <= t * j && aokiVotes[i - 1] <= a * j) {
          takahashiVotes[i] = t * j;
          aokiVotes[i] = a * j;
          sum = j * (t + a);
          break;
        }
      }
    } else {
      for (int j = 2; ;j++) {
        if (takahashiVotes[i - 1] <= t * j && aokiVotes[i - 1] <= a * j) {
          takahashiVotes[i] = t * j;
          aokiVotes[i] = a * j;
          sum = j * (t + a);
          break;
        }
      }
    }
  }
}

int main()
{
  int n, t, a;
  cin >> n;

  for (int i = 0; i < n; i++) {
    cin >> t >> a;
    if (i == 0) {
      takahashiVotes[i] = t;
      aokiVotes[i] = a;
      sum = t + a;
    } else {
      numberOfVotes(i, t, a);
    }
  }
  cout << sum << endl;

  return 0;
}
