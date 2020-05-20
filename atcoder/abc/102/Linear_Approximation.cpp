#include <iostream>
#include <vector>
#include <cmath>

using  namespace std;

typedef long long ll;
typedef long long int lli;

int main()
{
  ll n; cin >> n;
  vector<lli> iv;

  for (int i = 0; i < n; i++) {
    lli x; cin >> x;
    iv.push_back(x - i);
  }

  sort(iv.begin(), iv.end());

  // ivの中央値を計算する
  lli size = iv.size();
  lli b = iv[size / 2];

  lli sad = 0;

  for (lli x : iv) sad += abs(x - b);

  cout << sad << endl;

  return 0;
}
