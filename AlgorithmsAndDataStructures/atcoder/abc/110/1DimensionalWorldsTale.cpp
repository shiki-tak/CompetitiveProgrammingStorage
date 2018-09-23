#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int n, m, X, Y; cin >> n >> m >> X >> Y;
    int maxX = X;
    int minY = Y;

    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        if (maxX < x) maxX = x;
    }
    for (int i = 0; i < m; i++) {
        int y; cin >> y;
        if (minY > y) minY = y;
    }

    cout << (maxX < minY ? "No War" : "War") << endl;

    return 0;
}