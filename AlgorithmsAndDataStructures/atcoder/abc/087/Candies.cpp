#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int n; cin >> n;
    int a[3][109];
    int visited[3][109];

    for (int i = 0; i <= 2; i++) {
        for (int j = 0; j <= n; j++) {
            visited[i][j] = 0;
            a[i][j] = 0;
        }
    }

    // search
    for (int i = 1; i <= 2; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> a[i][j];
            visited[i][j] = max(visited[i][j - 1] + a[i][j], visited[i - 1][j] + a[i][j]);
        }
    }

    cout << visited[2][n] << endl;

    return 0;
}