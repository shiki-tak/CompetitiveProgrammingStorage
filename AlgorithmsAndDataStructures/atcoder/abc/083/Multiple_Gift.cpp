#include <iostream>

using namespace std;

int main()
{
    long long x, y; cin >> x >> y;
    int cnt = 0;
    long long s = x;

    while(s <= y) {
        s *= 2;
        cnt++;
    }

    cout << cnt << endl;

    return 0;
}