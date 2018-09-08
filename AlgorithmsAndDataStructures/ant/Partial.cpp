#include <iostream>

using namespace std;

int a[10010];

bool sumNum(int sum, int i, int k, int n) {
    if (sum == k) return true;
    if (i == n) return false;
    return sumNum(sum + a[i], i + 1, k, n) || sumNum(sum, i + 1, k, n);
}

int main()
{
    int n; cin >> n;
    for (int i = 0; i < n; i++) cin >> a[i];
    int k; cin >> k;

    cout << (sumNum(0, 0, k, n) ? "Yes" : "No") << endl;

    return 0;
}