#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main() {
  int w, h, n, s;
  // xp[2]:長方形を作るx座標の左端、右端を表す（xp[0]: 右, xp[1]: 左）
  // yp[2]:長方形を作るy座標の上端、下端を表す（yp[0]: 上, yp[1]: 下）
  int xp[2], yp[2];

  cin >> w >> h >> n;

  // 初期位置をセット
  xp[0] = w;
  xp[1] = 0;
  yp[0] = h;
  yp[1] = 0;

  for (int i = 0; i < n; i++) {
    int x, y, a;
    cin >> x >> y >> a;

    if (a == 1 && xp[1] < x)      xp[1] = x;
    else if (a == 2 && xp[0] > x) xp[0] = x;
    else if (a == 3 && yp[1] < y) yp[1] = y;
    else if (a == 4 && yp[0] > y) yp[0] = y;
  }

  w = xp[0] - xp[1];
  h = yp[0] - yp[1];

  if (w < 0 || h < 0) cout << 0 << endl;
  else cout << w * h << endl;

  return 0;
}
