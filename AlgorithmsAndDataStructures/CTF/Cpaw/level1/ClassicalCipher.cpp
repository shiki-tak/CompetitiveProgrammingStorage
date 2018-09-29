#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s = "fsdz{Fdhvdu_flskhu_lv_fodvvlfdo_flskhu}";

    for (int i = 0; i < s.size(); i++) {
        if ((s[i] >= 97 && s[i] <= 122) || (s[i] >= 65 && s[i] <= 90)) {
            char c = s[i] - 3;
            cout << c;
        } else {
            cout << s[i];
        }
    }
    cout << endl;
    return 0;
}