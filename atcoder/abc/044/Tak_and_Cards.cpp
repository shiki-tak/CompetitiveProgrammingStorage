#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int x[51];
int n, a;

int search(int i, int sum, int z)
{
  int res;
  if (i == n) {
    if (z != 0) {
      if (z * a == sum) return 1;
      else return 0;
    } else {
      return 0;
    }
  }
  
  res = search(i + 1, sum, z) + search(i + 1, sum + x[i], z + 1);

  return res;
}

int main()
{
  int cnt;
  cin >> n >> a;
  for (int i = 0; i < n; i++) {
    cin >> x[i];
  }
  cnt = search(0, 0, 0);
  cout << cnt << endl;
}
