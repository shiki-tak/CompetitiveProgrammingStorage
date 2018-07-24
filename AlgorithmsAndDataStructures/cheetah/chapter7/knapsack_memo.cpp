#include <iostream>
#include <cmath>

using namespace std;

int weight[] = {3, 4, 1, 2, 3};
int value[] = {2, 3, 2, 3, 6};
int dp[6][11];

int search(int n, int w) 
{

    if (w > 10) return -1;
    if (n >= 5) return 0;
    if (dp[n][w] >= 0) {
        return dp[n][w];
    }
    return dp[n][w] = max(search(n + 1, w), search(n + 1, w + weight[n]) + value[n]);
}

int main()
{
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 11; j++) {
            dp[i][j] = -1;
        }
    }
    cout << search(0, 0) << endl;

    return 0;
}