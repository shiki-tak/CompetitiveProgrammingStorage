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
        for (int j = s.length() - 1; j >= 0; j--) {
            if (j == 0) s[j] = x;
            else        s[j] = s[j - 1];
        }
    }

    cout << ret << endl;

    return 0;
}