#include <iostream>
#include <vector>

using namespace std;

int h = 0, w = 0;
char a[101][101];
vector<int> rmRow;
vector<int> rmCol;

void checkRow() {

    for (int i = 0; i < w; i++) {
        bool rowFlag = true;

        for (int j = 0; j < h; j++) {
            if (a[j][i] == '#') { rowFlag = false; }
        }
        if (rowFlag) rmRow.push_back(i);
    }
}

bool findRmTarget() {
    bool flag = true;

    for (int i = 0; i < )


    return flag;
}


int main()
{
    cin >> h >> w;

    for (int i = 0; i < h; i++) {
        bool colFlag = true;

        for (int j = 0; j < w; j++) {
            cin >> a[i][j];
            if (a[i][j] == '#') { colFlag = false; }
        }
        if (colFlag) rmCol.push_back(i);
    }

    checkRow();


    cout << "+++ rm col +++" << endl;

    for (int i = 0; i < rmCol.size(); i++) {
        cout << rmCol[i] << endl;
    }

    cout << "+++ rm row +++" << endl;

    for (int i = 0; i < rmRow.size(); i++) {
        cout << rmRow[i] << endl;
    }

    for (int i = 0; i < h; i++) {
        auto colItr = find(rmCol.begin(), rmCol.end(), i);
        if (colItr != rmCol.end()) continue;   // 見つからなければcontinue
        for (int j = 0; j < w; j++) {
            auto rowItr = find(rmRow.begin(), rmRow.end(), j);
            if (rowItr != rmRow.end()) continue;

            if (j == w - 1) {
                cout << a[i][j] << endl;
            } else {
                cout << a[i][j];
            }
        }
    }

    return 0;
}