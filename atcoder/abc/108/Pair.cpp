#include <iostream>

using namespace std;

int main()
{
    int k; cin >> k;
    
    cout << ((k % 2 == 0) ? (k / 2) * (k / 2) : (k / 2 + 1) * (k / 2)) << endl;
    return 0;
}