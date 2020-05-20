#include <iostream>

using namespace std;

int main()
{
    int n, k; cin >> n >> k;
    int cnt = 0;
    int a[100010] = {0};

    // 最小値である1がどこにあるかに関係なく、置き換える回数が決まる
    // a / b の切り上げ計算
    // (a + (b - 1)) / b
    cnt = ((n - 1) + (k - 1) - 1) / (k - 1); 

    // よって、配列への標準入力は不要なのだが...
    for (int i = 1; i <= n; i++) cin >> a[i];
    cout << cnt << endl;

    return 0;
}