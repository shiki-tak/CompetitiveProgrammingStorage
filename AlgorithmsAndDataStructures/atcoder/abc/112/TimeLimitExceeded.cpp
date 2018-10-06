#include <iostream>

using namespace std;

typedef pair<int , int> P;

int main()
{
    int n, t; cin >> n >> t;
    int ans = 1001;

    P ct;
    for (int i = 0; i < n; i++) {
        cin >> ct.first >> ct.second;
        if (ct.second <= t && ct.first < ans) {
            ans = ct.first;
        }
    }

    if (ans == 1001) cout << "TLE" << endl;
    else cout << ans << endl;

    return 0;
}