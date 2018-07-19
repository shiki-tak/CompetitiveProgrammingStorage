#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s;  cin >> s;

    int s_len = s.length();
    int ans = s_len; // 初めから回文であれば、文字列の長さがそのまま解となる

    int head = 0;
    int tail = s_len - 1;

    while(tail >= head) {
        if (s[head] == s[tail]) {
            head++;
            tail--;
        } else {
            ans++;
            head++;
        }
    }

    cout << ans << endl;

    return 0;
}