#include <iostream>
#include <cmath>

using std::cin;
using std::cout;
using std::endl;
using std::max;

int main() {
    int n;
    cin >> n;

    long long T = 1, A = 1;
    for (int i = 0; i < n; ++i) {
        long long t, a;
        cin >> t;
        cin >> a;
        // 投票数がt:aとなる最小の自然数kを探す
        // 投票数T, Aは T:A = k * t : k * a
        // 　　　　　　　→ k = max(T/t, A/a)

        long long k = max((t + T - 1) / t, (a + A - 1) / a);
        T = k * t;
        A = k * a;
    }

    cout << T + A << endl;
    return 0;
}
