#include <iostream>
#include <string>
#include <cmath>

using namespace std;

string s;

int search(int x, int n) {
    int ret = 0;
    for (int i = 0; i < n; i++) {
        if (i != x) {
            if (i < x) {
                if (s[i] == 'W') ret++;
            } else {
                if (s[i] == 'E') ret++;
            }
        }
    }
    return ret;
}

int main()
{
    int n; cin >> n;
    cin >> s;
    int ans = 0;

    int head = 0, tail = n - 1;

    while(true) {
        int center = (head + tail) / 2;
        // cout << "head: " << head << endl;
        // cout << "center: " << center << endl;
        // cout << "tail: " << tail << endl;

        // cout << "s[" << (head + center) / 2 << "] = " << search((head + center) / 2, n) << endl;
        // cout << "s[" << (center + tail) / 2 << "] = " << search((center + tail) / 2, n) << endl;

        if (head == center || tail == center) {
            ans = min(search(head, n), search(tail, n));
            break;
        }
        if (search((head + center) / 2, n) < search((center + tail) / 2, n)) {
            tail = center;
       } else {
           head = center;
       }
       ans = min(search((head + center) / 2, n), search((center + tail) / 2, n));
    }

    cout << ans << endl;

    return 0;
}