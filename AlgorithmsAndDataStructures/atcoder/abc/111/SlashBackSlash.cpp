#include <iostream>

using namespace std;
typedef pair<int, int> P;

int n = 0;
long oV[100001] = { 0 };
long eV[100001] = { 0 };

int main()
{
    cin >> n;
    P cntOdd, cntEven;    // 奇数、偶数番目の数字の出現回数
    P oddN, evenN;        // 出現回数の多い2つの数字を格納
    for (int i = 1; i <= n; i++) {
        int x; cin >> x;
        if (i % 2 != 0) oV[x] += 1;
        else            eV[x] += 1;
    }

    for (int i = 0; i < 100001; i++) {
        if (oV[i] != 0) {
            if (cntOdd.first <= oV[i]) {
                if (cntOdd.second < cntOdd.first) {
                    cntOdd.second = cntOdd.first;
                    oddN.second = oddN.first;
                }
                cntOdd.first = oV[i];
                oddN.first = i;
            }
            if (cntOdd.first > oV[i] && cntOdd.second < oV[i]) {
                cntOdd.second = oV[i];
                oddN.second = i;
            }
        }

        if (eV[i] != 0) {
            if (cntEven.first <= eV[i]) {
                if (cntEven.second < cntEven.first) {
                    cntEven.second = cntEven.first;
                    evenN.second = evenN.first;
                }
                cntEven.first = eV[i];
                evenN.first = i;
            }
            if (cntEven.first > eV[i] && cntEven.second < eV[i]) {
                cntEven.second = eV[i];
                evenN.second = i;
            }
        }
    }

    if (oddN.first != evenN.first) {
        int ans = n / 2 - cntOdd.first + n / 2 - cntEven.first;
        cout << ans << endl;
    } else {
        int ansCandidate1 = n / 2 - cntOdd.first + n / 2 - cntEven.second;
        int ansCandidate2 = n / 2 - cntOdd.second + n / 2 - cntEven.first;

        cout << ((ansCandidate1 < ansCandidate2) ? ansCandidate1 : ansCandidate2) << endl;
    }
    return 0;
}
