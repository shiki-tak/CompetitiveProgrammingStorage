#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int x; cin >> x;
    int ret = 1;

        for (int i = 1; i <= x; i++) {
            for (int j = 2; j <= x; j++) {
                if (pow(i, j) > x) break;
                ret = max(ret, int(pow(i, j)));
            }
    }

    cout << ret << endl;
    return 0;
}