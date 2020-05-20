#include <iostream>
#include <string>

using namespace std;

int main()
{
    int n; cin >> n;

    for (int i = n; i <= 999; i++) {
        string s = to_string(n);
        if (s[0] == s[1] && s[1] == s[2]) {
            break;
        } else {
            n += 1;
        }
    }

    cout << n << endl;

    return 0;
}