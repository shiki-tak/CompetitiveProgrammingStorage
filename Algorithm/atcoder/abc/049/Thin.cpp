#include <iostream>

using std::cin;
using std::cout;
using std::endl;

char c[101][101];
char cT[201][101];

int main()
{
  int h, w;
  cin >> h >> w;

  for (int i = 1; i <= 2 * h; i++) {
    for (int j = 1; j <= w; j++) {
      // iがh以下の場合、標準入力
      if (i <= h) cin >> c[i][j];
      // 縦長
      cT[i][j] = c[(i + 1) / 2][j];
      if (j == w) cout << cT[i][j] << endl;
      else        cout << cT[i][j];
    }
  }
}
