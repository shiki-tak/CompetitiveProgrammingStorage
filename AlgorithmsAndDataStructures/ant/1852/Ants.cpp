#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int l, n; cin >> l >> n;
    int minT = 0, maxT = 0;
    vector<int> antPositions;

    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        antPositions.push_back(x);
    }

    for (int i = 0; i < n; i++){
        minT = max(min(l - antPositions[i], antPositions[i]), minT);
        maxT = max(max(l - antPositions[i], antPositions[i]), maxT);
    }

    cout << minT << " " << maxT << endl;

    return 0;
}