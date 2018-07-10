#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main() {
  int w, h, n, s;
  // 長方形を作るx座標の左端、右端を表す（xp[0]: 右, xp[1]: 左）
  int xp[2];
  // 長方形を作るy座標の上端、下端を表す（yp[0]: 上, xp[1]: 下）
  int yp[2];
  cin >> w >> h >> n;

  xp[0] = w;
  xp[1] = 0;
  yp[0] = h;
  yp[1] = 0;

  for (int i = 0; i < n; i++) {
    int x, y, a;
    cin >> x >> y >> a;

    if (a == 1)      xp[1] = x;
    else if (a == 2) xp[0] = x;
    else if (a == 3) yp[1] = y;
    else             yp[0] = y;
  }

  if ((xp[0] - xp[1]) <= 0 || (yp[0] - yp[1] <= 0)) {
    cout << 0 << endl;
  } else {
    s = (xp[0] - xp[1]) * (yp[0] - yp[1]);
    cout << s << endl;
  }

  return 0;
}
