#include <iostream>
#include <string>

using namespace std;

char check(int card[], int number) {
    for (int i = 0; i < 8; i++) {
        if (card[i] == number) return 'Y';
    }
    return 'N';
}

int main()
{
    int card1[] = {1, 2, 3, 4, 5, 6, 7, 8};
    int card2[] = {1, 2, 3, 4, 9, 10, 11, 12};
    int card3[] = {1, 2, 5, 6, 9, 10, 13, 14};
    int card4[] = {1, 3, 5, 7, 9, 11, 13, 15};

    string ans; cin >> ans;
    int ret = 0;

    for (int i = 1; i <=16; i++) {
        if (check(card1, i) != ans[0]) continue;
        if (check(card2, i) != ans[1]) continue;
        if (check(card3, i) != ans[2]) continue;
        if (check(card4, i) != ans[3]) continue;
        ret = i;
    }

    cout << ret << endl;

    return 0;
}