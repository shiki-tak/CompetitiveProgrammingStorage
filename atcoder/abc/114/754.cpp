#include <iostream>
#include <cmath>
#include <string>

using namespace std;

int main() {
    string s; cin >> s;
    int ans = 1000000;
    for (int i = 0; i < s.length() - 2; i++) {
        int a = s[i] - '0';
        int b = s[i + 1] - '0';
        int c = s[i + 2] - '0';
        int x = 100 * a + 10 * b + c;
        ans = min(ans, abs(x - 753));
    }
    cout << ans << endl;
    return 0;
}