#include <iostream>
#include <vector>

using namespace std;

int gcd(int x, int y) {
    if (x < y) {
        int tmp = x;
        x = y;
        y = tmp;
    }
    int r = -1;

    while(r != 0) {
        r = x % y;
        x = y;
        y = r;
    }
    return x;
}

int check(vector<int> skip) {
    int ans = gcd(skip[0], skip[1]);
    for (int i = 1; i < skip.size() - 1; i++) {
        ans = gcd(ans, skip[i + 1]);
    }
    return ans;
}

int main()
{
    int n, x; cin >> n >> x;
    vector<int> skip;

    for (int i = 0; i < n; i++) {
        int cp; cin >> cp;
        skip.push_back(abs(cp - x));
    }

    cout << (skip.size() == 1 ? skip[0] : check(skip)) << endl;

    return 0;
}