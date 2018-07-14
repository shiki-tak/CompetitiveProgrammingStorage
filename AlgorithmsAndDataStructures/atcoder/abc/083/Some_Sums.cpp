#include <iostream>
#include <string>

using namespace std;

int main()
{
  int n, a, b, sum;

  cin >> n >> a >> b;

  // 判定する
  for (int i = 0; i <= n; i++) {
    int num = 0;
    string strN = to_string(i);

    for (int j = 0; j < strN.length(); j++) {
      char cN = strN[j];
      num += int(cN) - 48;
    }
    if (num >= a && num <= b) sum += i;
  }

  cout << sum << endl;

  return 0;
}
