#include <iostream>
#include <cstdlib>

using namespace std;

int main()
{
    int n; cin >> n;
    int t[n], x[n], y[n];

    bool traveling = true;

    for (int i = 0; i < n; i++) cin >> t[i] >> x[i] >> y[i];

    for (int i = 0; i < n; i++) {

        int dt = 0, ds = 0;

        if (i == 0) {
            dt = t[i];
            ds = x[i] + y[i];
        } else {
            dt = t[i] - t[i - 1];
            ds = abs(x[i] - x[i - 1]) + abs(y[i] - y[i - 1]);
        }
        if (dt < ds) {
            traveling = false;
            break;
        } else if (ds % 2 == 0 && dt % 2 != 0) {
            traveling = false;
            break;
        } else if (ds % 2 != 0 && dt % 2 == 0) {
            traveling = false;
            break;
        }
    }

    if (traveling) cout << "Yes" << endl;
    else           cout << "No" << endl;

    return 0;
}