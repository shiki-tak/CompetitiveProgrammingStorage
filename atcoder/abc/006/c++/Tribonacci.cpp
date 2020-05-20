#include <iostream>

using namespace std;

int main()
{
    int n; cin >> n;
    long long a[1000010] = {0};
    a[3] = 1;

    for (int i = 4; i <= n; i++) {
        if (a[i] != 0) continue;
        else           a[i] = (a[i - 1] + a[i - 2] + a[i - 3]) % 10007;
    }

    cout << a[n] << endl;
    return 0;
}