#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s; cin >> s;
    replace(s.begin(), s.end(), ',', ' ');

    cout << s << endl;

    return 0;
}