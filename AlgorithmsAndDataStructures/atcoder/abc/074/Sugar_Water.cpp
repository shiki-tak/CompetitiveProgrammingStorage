#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int a, b, c, d, e, f; cin >> a >> b >> c >> d >> e >> f;

    int i = 0, j = 0;
    float ret = 0;
    int maxX = 0;
    int maxY = 0;
    bool full = false;

    float x = 0;
    float y = 0;

    for (int i = 0; ;i++) {
        for (int j = 0;;j++) {

            cout << "i: " << i << endl;
            cout << "j: " << j << endl;

            x = 100 * a * i + 200 * b * j;
            y = c * i + d * j;

            if (x + y >= f) {
                cout << "いっぱい" << endl;
                cout << "x: " << x << endl;
                cout << "y: " << y << endl;

                x = 100 * a * i + 200 * b * (j - 1);
                y = c * i + d * (j - 1);
                break;
            }
        }
        // x, yのどちらも0だった場合、濃度が計算できない
        if ((x != 0 || y != 0) && 100 * y <= e * x) {

            cout << "x: " << x << endl;
            cout << "y: " << y << endl;

            if (ret < (100 * y) / (x + y)) {
                ret = (100 * y) / (x + y);
                cout << "ret: " << ret << endl;
                maxX = x;
                maxY = y;
                continue;
            } else {
                break;
            }
        }
    }

    cout << (maxX + maxY) << " " << maxY << endl;

    return 0;
}