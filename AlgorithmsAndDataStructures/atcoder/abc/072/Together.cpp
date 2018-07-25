#include <iostream>
#include <cmath>
 
using namespace std;
 
int main()
{
    int countNum[100010] = {0};
    int maxCount = 0;
 
    int n; cin >> n;
    for (int i = 1; i <= n; i++) {
        int a; cin >> a;
        countNum[a]++;
        if (a > 0) countNum[a - 1]++;
        if (a < 100000) countNum[a + 1]++;
     }
 
    for (int i = 0; i <=100000; i++) {
        if (i != 0) maxCount = max(max(maxCount, countNum[i]), max(countNum[i - 1], countNum[i + 1]));
        else        maxCount = max(max(maxCount, countNum[i]), countNum[i + 1]);
    }

    cout << maxCount << endl;
 
    return 0;
}