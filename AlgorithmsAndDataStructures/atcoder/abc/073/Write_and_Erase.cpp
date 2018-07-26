#include <iostream>
#include <map>
#include <string>

using namespace std;

typedef pair<int, int> WESet;

int main()
{
    int n; cin >> n;
    int ret = 0;
    map<string, int> a;

    for (int i = 0; i < n; i++) {
        string x; cin >> x;
        a[x] += 1;
        if (a[x] % 2 != 0)      ret++;
        else if (a[x] % 2 == 0) ret--;
    }

    cout << ret << endl;

    return 0;
}