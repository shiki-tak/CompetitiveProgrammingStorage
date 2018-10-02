#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

const int MAX_VALUE = 100000000;
using namespace std;

int n = 0, k = 0;
bool bucket[MAX_VALUE];
vector<int> c;

int main()
{
    int ans = 0;
    cin >> n >> k;
    for (int i = 0; i < MAX_VALUE; i++) bucket[i] = true;

    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        c.push_back(x);
    }

    sort(c.begin(), c.end());   // これ絶対いるヨ！！

    do {
        int num = 0;
        for (int i = 0; i < k; i++) {
            int x = c[i];
            if (i != 0) {
                if (x < 10) num *= 10;
                else        num *= 100;
            }
            num += x;
        }
        if (bucket[num]) {
            ans++;
            bucket[num] = false;
        }

    } while(next_permutation(c.begin(), c.end()));

    cout << ans << endl;

    return 0;
}