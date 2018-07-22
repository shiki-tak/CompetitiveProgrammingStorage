#include <iostream>

using namespace std;

int main()
{
    int n, m; cin >> n >> m;
    int x = 0, y = 0, z = 0;
    bool flag = false;

    // xが決まった後のyとzの関係式は以下の通り
    // y + z = n - x;
    // 5000 * y + 1000 * z = m - 10000 * x;

    for (int i = 0; i <= n; i++) {
        x = i;
        for (int j = 0; j <= n - i; j++) {
            y = j;
            if (10000 * i + 5000 * j + 1000 * (n - i - j) == m) {
                z = n - i - j;
                flag = true;
                break;
            }
        } if (flag) break;
    }

    if (flag) cout << x << " " << y << " " << z << endl;
    else      cout << -1 << " " << -1 << " " << -1 << endl;

    return 0;
}