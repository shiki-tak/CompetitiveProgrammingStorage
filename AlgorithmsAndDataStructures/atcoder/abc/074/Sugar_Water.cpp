#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int a, b, c, d, e, f; cin >> a >> b >> c >> d >> e >> f;

    float ret = -1;
    int maxX = 0, maxY = 0;
    float x = 0, y = 0;

    for (int i = 0; i < 30; i++) {
        for (int j = 0; j < 30; j++) {

            x = 100 * a * i + 200 * b * j;
            if (x > f) break;

            for (int k = 0; ; k++) {
                if (x + c * k > f) break;

                for (int l = 0; ; l++) {
                    y  = c * k + d * l;
                    if (x + y > f) break;

                    if (x != 0 && (100 * y) / x <= e) {
                        if (ret < (100 * y) / (x + y)) {
                            ret = (100 * y) / (x + y);
                            maxX = x;
                            maxY = y;
                        }
                    }                   
                }
            }
        }
    }

    cout << (maxX + maxY) << " " << maxY << endl;

    return 0;
}