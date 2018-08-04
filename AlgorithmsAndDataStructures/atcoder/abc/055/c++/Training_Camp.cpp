#include <iostream>

using namespace std;

int main()
{
    int n; cin >> n;
    long long p = 1;

    for (int i = 1; i <= n; i++) {
        p *= i;
        p %= 1000000007;
    }

    cout << p << endl;

    return 0;
}