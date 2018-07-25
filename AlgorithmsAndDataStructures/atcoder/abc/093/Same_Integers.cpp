#include <iostream>
#include <vector>

using namespace std;

int main()
{
    vector<int> v;
    int cnt = 0;

    for (int i = 0; i < 3; i++) {
        int x; cin >> x;
        v.push_back(x);
    }
    sort(v.begin(), v.end());

    // sortして、それぞれの数の差が奇数か偶数で場合分け
    
    // cout << "even, even" << endl;
    if ((v[1] - v[0]) % 2 == 0 && (v[2] - v[0]) % 2 == 0) {
        cnt = ((v[2] - v[1]) / 2) + ((v[2] - v[0]) / 2);

    // cout << "even, odd" << endl;
    } else if ((v[1] - v[0]) % 2 == 0 && (v[2] - v[1]) % 2 != 0) {
        cnt = ((v[1] - v[0]) / 2) + 2 * (v[2] - (v[1] + 1)) / 2 + 1;

    // cout << "odd, even" << endl;
    } else if ((v[1] - v[0]) % 2 != 0 && (v[2] - v[1]) % 2 == 0) {
        cnt = (((v[2] + 1) - v[0]) / 2) + ((v[2] - v[1]) / 2) + 1;

    // cout << "even, even" << endl;
    } else if ((v[1] - v[0]) % 2 != 0 && (v[2] - v[1]) % 2 != 0) {
        cnt = ((v[2] - v[0]) / 2) + (((v[2] + 1) - v[1]) / 2) + 1;
    }
    cout << cnt << endl;

    return 0;
}