#include <iostream>

using namespace std;

int main()
{
    char a, b; cin >> a >> b;
    cout << ((a == 'H' && b == 'H') ? 'H' : (a == 'H' && b == 'D' ? 'D' : (a == 'D' && b == 'H' ? 'D' : 'H'))) << endl;
    return 0;
}