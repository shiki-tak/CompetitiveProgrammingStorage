#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int n; cin >> n;
    char friends[n][n];
    int friends_number = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> friends[i][j];
        }
    }

    // search friends
    for (int i = 0; i < n; i++) {
        int x = 0;
        for (int j = 0; j < n; j++) {
            if (friends[i][j] == 'Y') {
                x += 1;
                // i番目の人の友人がjだった場合j番目の交友関係を調べて、i番目の友達の友達の数をカウントする
                for (int k = 0; k < n; k++) {
                    if (friends[j][k] == 'Y' && k != i && friends[i][k] =='N') x += 1;
                }
            }
        }
        friends_number = max(friends_number, x);
    }

    cout << friends_number << endl;

    return 0;
}