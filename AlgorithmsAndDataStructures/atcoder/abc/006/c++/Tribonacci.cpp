#include <iostream>

using namespace std;

int calcTribonacci(int x, int a[]) {

    if (x == 1 || x == 2) {
        return 0;
    } else {
        if (a[x] != 0) {
            return a[x];
        } else {
            a[x] = calcTribonacci(x - 1, a) + calcTribonacci(x - 2, a) + calcTribonacci(x - 3, a);
        }
        return a[x];
    }
}

int main()
{
    int n; cin >> n;
    int a[1000010] = {0};
    a[3] = 1;

    cout << calcTribonacci(n, a) % 10007 << endl;

    return 0;
}