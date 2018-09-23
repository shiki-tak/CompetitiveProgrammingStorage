#include <iostream>
#include <vector>

using namespace std;

int main()
{
    vector<int> v;

    for (int i = 0; i < 3; i++) {
        int x; cin >> x;
        v.push_back(x);
    }
    sort(v.begin(), v.end(), greater<int>());

    cout << v[0] * 10 + v[1] + v[2] << endl;

    return 0;
}