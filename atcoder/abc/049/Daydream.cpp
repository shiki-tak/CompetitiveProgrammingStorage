#include <iostream>
#include <string>

using namespace std;

string s;

bool checkDream(int i)
{
    bool flag = true;

    if (s[i] != 'm') flag = false;
    if (s[i - 1] != 'a') flag = false;
    if (s[i - 2] != 'e') flag = false;
    if (s[i - 3] != 'r') flag = false;
    if (s[i - 4] != 'd') flag = false;

    return flag;
}

bool checkErase(int i)
{
    bool flag = true;

    if (s[i] != 'e') flag = false;
    if (s[i - 1] != 's') flag = false;
    if (s[i - 2] != 'a') flag = false;
    if (s[i - 3] != 'r') flag = false;
    if (s[i - 4] != 'e') flag = false;

    return flag;
}
 
int main()
{
    cin >> s;
    bool breakFlag = true;

    int i = s.length() - 1;

    for ( ; i > 0; ) {
        if (s[i] == 'm') {
            if (checkDream(i)) {
                i -= 5;
                continue;
            } else {
                breakFlag = false;
                break;
            }

        } else if (s[i] == 'e') {
            if (checkErase(i)) {
                i -= 5;
            } else {
                breakFlag = false;
                break;
            }
        } else if (s[i] == 'r') {
            if (s[i - 2] == 'm') {
                if (checkDream(i - 2)) {
                    i -= 7;
                } else {
                    breakFlag = false;
                    break;
                }

            } else if (s[i - 2] == 's') {
                if (checkErase(i - 1)) {
                    i -= 6;
                } else {
                    breakFlag = false;
                    break;
                }
            } else {
                breakFlag = false;
                break;
            }
        } else {
            breakFlag = false;
            break;
        }
    }

    if (breakFlag) cout << "YES" << endl;
    else           cout << "NO" << endl;

    return 0;
}