#include <iostream>

using namespace std;

int main()
{
    int a, b, c, d; cin >> a >> b >> c >> d;
    // 三項演算子をネストさせてみた
    cout << (a + b == c + d ? "Balanced" : a + b > c + d ? "Left" : "Right") << endl;
    return 0;
}