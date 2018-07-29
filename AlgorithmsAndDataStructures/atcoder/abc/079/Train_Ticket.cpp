#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s; cin >> s;
    int abcd[4];

    for (int i =  0; i < 4; i++) abcd[i] = s[i] - '0';

    if (abcd[0] + abcd[1] + abcd[2] + abcd[3] == 7) {
        cout << abcd[0] << "+" << abcd[1] << "+" << abcd[2] << "+" << abcd[3] << "=7" << endl;

    } else if (abcd[0] + abcd[1] + abcd[2] - abcd[3] == 7) {
        cout << abcd[0] << "+" << abcd[1] << "+" << abcd[2] << "-" << abcd[3] << "=7" << endl;

    } else if (abcd[0] + abcd[1] - abcd[2] - abcd[3] == 7) {
        cout << abcd[0] << "+" << abcd[1] << "-" << abcd[2] << "-" << abcd[3] << "=7" << endl;

    } else if (abcd[0] + abcd[1] - abcd[2] + abcd[3] == 7) {
        cout << abcd[0] << "+" << abcd[1] << "-" << abcd[2] << "+" << abcd[3] << "=7" << endl;

    } else if (abcd[0] - abcd[1] + abcd[2] + abcd[3] == 7) {
        cout << abcd[0] << "-" << abcd[1] << "+" << abcd[2] << "+" << abcd[3] << "=7" << endl;

    } else if (abcd[0] - abcd[1] - abcd[2] + abcd[3] == 7) {
        cout << abcd[0] << "-" << abcd[1] << "-" << abcd[2] << "+" << abcd[3] << "=7" << endl;

    } else if (abcd[0] - abcd[1] + abcd[2] - abcd[3] == 7) {
        cout << abcd[0] << "-" << abcd[1] << "+" << abcd[2] << "-" << abcd[3] << "=7" << endl;

    } else if (abcd[0] - abcd[1] - abcd[2] - abcd[3] == 7) {
        cout << abcd[0] << "-" << abcd[1] << "-" << abcd[2] << "-" << abcd[3] << "=7" << endl;
    }

    return 0;
}