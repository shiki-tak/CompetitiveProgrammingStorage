#include <iostream>
#include <cstdlib>

using namespace std;

int main()
{
    int n; cin >> n;
    int t[n + 1], x[n + 1], y[n + 1];
    t[0] = 0, x[0] = 0, y[0] = 0;

    bool traveling = true;

    for (int i = 1; i <= n; i++) {
        cin >> t[i] >> x[i] >> y[i];
        // 時刻iと時刻i-1の差分を計算
        int dt = t[i] - t[i - 1];
        int ds = abs(x[i] - x[i - 1]) + abs(y[i] - y[i - 1]);

        // 旅行がプラン実行が不可能な場合
        if ((dt < ds) || (ds % 2 == 0 && dt % 2 != 0) || (ds % 2 != 0 && dt % 2 == 0)) traveling = false;
    }

    if (traveling) cout << "Yes" << endl;
    else           cout << "No" << endl;

    return 0;
}