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
            x = 100 * a * i + 200 * b * j;
            y = c * i + d * j;

            cout << "i: " << i << endl;
            cout << "j: " << j << endl;

            if (x + y > f) {
                // continue;
                full = true;
                break;

            } else {
                if (i != 0 || j != 0 ) {
                    cout << "x: " << x << endl;
                    cout << "y: " << y << endl;
                    if (ret < (100 * y) / (x + y)) {
                        ret = (100 * y) / (x + y);
                        maxX = x;
                        maxY = y;
                    }
                }
            }
        }
        if (full && ret == 0) continue;
        else                  break;
    }

    cout << (x + y) << " " << y << endl;

    return 0;
}