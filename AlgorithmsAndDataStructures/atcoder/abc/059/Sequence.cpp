#include <iostream>
#include <cstdlib>

using namespace std;

long long sum[2] = {0};

long long check(int n, int s, long long a[]) {
    long long cnt = 0;
    // 条件を満たすように修正した要素を格納するための配列
    long long na[n];

    for (int i = 0; i < n; i++) {
        na[i] = a[i];

        // 偶数番目のsumが正の場合の操作回数をカウントする
        if (s == 0) {
            sum[s] += na[i];
            // 偶数番目のsumが負の場合、差分を計算する
            if (i % 2 == 0 && sum[s] <= 0) {
                // 変更する差分
                long long dx = 1 - sum[s];
                na[i] += dx;
                cnt += abs(dx);
                sum[s] += dx;
            // 奇数番目のsumが正の場合、差分を計算する
            } else if (i % 2 != 0 && sum[s] >= 0) {
                // 変更する差分
                long long dx = -1 - sum[s];
                na[i] += dx;
                cnt += abs(dx);
                sum[s] += dx;
            }
        }
        // 奇数番目のsumが正の場合の操作回数をカウントする
        if (s == 1) {
            sum[s] += na[i];
            // 奇数番目のsumが負の場合、差分を計算する
            if (i % 2 != 0 && sum[s] <= 0) {
                // 変更する差分
                long long dx = 1 - sum[s];
                na[i] += dx;
                cnt += abs(dx);
                sum[s] += dx;
            // 偶数番目のsumが正の場合、差分を計算する
            } else if (i % 2 == 0 && sum[s] >= 0) {
                // 変更する差分
                long long dx = -1 - sum[s];
                na[i] += dx;
                cnt += abs(dx);
                sum[s] += dx;
            }
        }
    }
    return cnt;
}

int main()
{
    int n; cin >> n;
    long long a[100001];

    for (int i = 0; i < n; i++) cin >> a[i];
    // a[0]のsumを正、負のどちらにすれば最小になるかを計算する
    cout <<min(check(n, 0, a), check(n, 1, a)) << endl;

    return 0;
}
