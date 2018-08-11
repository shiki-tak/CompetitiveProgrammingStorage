#include <iostream>

using namespace std;

int main()
{
    int n; cin >> n;
    bool nothing = false;

    for (int i = 0; ;i++) {
        int sum = 4 * i;
        if (sum > n) break;
        for (int j = 0; ; j++) {
            sum += 7 * j;
            if (sum > n) break;
            else if (sum == n) nothing = true;
        }
        if (nothing) break;
    }
    cout << (nothing ? "Yes" : "No") << endl;

    return 0;
}