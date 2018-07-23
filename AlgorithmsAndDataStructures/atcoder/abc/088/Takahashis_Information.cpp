#include <iostream>

using namespace std;

int main()
{
    int c[4][4] = {0};
    bool ansFlag = false;

    int a1, a2, a3, b1, b2, b3;

    for (int i = 1; i <= 3; i++) {
        for (int j = 1; j <= 3; j++) {
            cin >> c[i][j];
        }
    }

    for (int i = 0; i <= 100; i++) {
        // a1 = iで固定した場合のa2, a3, b1〜b3の関係は以下の通り
        a1 = i;
        b1 = c[1][1] - i;
        a2 = c[2][1] - c[1][1] + i;
        b2 = c[1][2] - i;
        a3 = c[3][1] - c[1][1] + i;
        b3 = c[1][3] - i;

        // 上記の関係式を満たすaijであってもcijの関係を満たさない場合がある
        // もし、満たすaijがあれば、答えは"Yes"となる
        if ((a1 + b1 == c[1][1]) && (a1 + b2 == c[1][2]) && (a1 + b3 == c[1][3]) 
             && (a2 + b1 == c[2][1]) && (a2 + b2 == c[2][2]) && (a2 + b3 == c[2][3])
             && (a3 + b1 == c[3][1]) && (a3 + b2 == c[3][2]) && (a3 + b3 == c[3][3])) {
            ansFlag = true;
            break;
        }
    }

    if (ansFlag) cout << "Yes" << endl;
    else         cout << "No" << endl;

    return 0;
}