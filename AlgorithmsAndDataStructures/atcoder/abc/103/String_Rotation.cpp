#include <iostream>
#include <string>

using namespace std;

string s, t;

int main()
{
    cin >> s >> t;
    string ret = "No";

    for (int i = 0; i < s.length(); i++) {
        if (s == t) {
            ret = "Yes";
            break;
        }
        // rotation
        char x = s[s.length() - 1];
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i == 0) s[i] = x;
            else        s[i] = s[i - 1];
        }
    }

    cout << ret << endl;

    return 0;
}