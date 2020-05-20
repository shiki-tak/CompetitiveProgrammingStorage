#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main()
{
    string a, b; cin >> a >> b;
    int t = atoi((a + b).c_str());
    int x = sqrt(t);

    cout << (x * x == t ? "Yes" : "No") << endl;

    return 0;
}