#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main()
{
    int a, b, n; cin >> a >> b >> n;
    string x; cin >> x;

    for (int i = 0; i < n; i++) {
        if ((x[i] == 'S' && a > 0) || (x[i] == 'E' && (a > 0 || b > 0) && a >= b))     a -= 1;
        else if ((x[i] == 'C' && b > 0) || (x[i] == 'E' && (a > 0 || b > 0) && a < b)) b -= 1;
    }
    cout << a << endl;
    cout << b << endl;

    return 0;
}