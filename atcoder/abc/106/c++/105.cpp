#include <iostream>

using namespace std;

int main()
{
    int n; cin >> n;
    int ans = 0;

    // i: target number
    for (int i = 1; i <= n; i +=2) {
        int divisorCount = 0;
        for (int j = 1; j <= i; j++) {
            if (i % j == 0) divisorCount++;
        }
        if (divisorCount == 8) ans++;
    }

    cout << ans << endl;
    return 0;
}