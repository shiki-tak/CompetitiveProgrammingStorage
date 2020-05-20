#include <iostream>

using namespace std;

int main()
{
    int n, d; cin >> n >> d;
    int x[n + 1];
    int cnt = 0;
    int center = 0;

    for (int i = 1; i <= n; i++) cin >> x[i];

    for (int i = 1; i <= n - 2; i++) {
        center = (i + n) / 2;
        if (x[center] - x[i] > d) {
            center--;
        }


        if (x[n] - x[i] < d) break;


        // if (x[n] - x[i] < d) break;
        // for (int j = i + 1; j <= n - 1; j++) {
        //     if (x[j] - x[i] > d) break;
        //     for (int k = j + 1; k <= n; k++) {
        //         if (x[j] - x[i] <= d && x[k] - x[j] <= d && x[k] - x[i] > d) {
        //             cnt++;
        //         }
        //     }
        // }
    }
    
    cout << cnt << endl;

    return 0;
}