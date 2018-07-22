#include <iostream>

using namespace std;

int weight[] = {3, 4, 1, 2, 3};
int value[] = {2, 3, 2, 3, 6};

// 分割統治法でナップサック問題を解く
int search(int i, int w, int sum) {
    // cout << "i: " << i << ", " << "w: " << w << " , sum: " << sum << endl;

    if (i > 4)   return sum;
    if (w >= 10) return  sum;

    int ret = max(search(i + 1, w, sum), search(i + 1, w + weight[i], sum + value[i]));

    return ret;
}

int main()
{
    cout << search(0, 0, 0) << endl;

    return 0;
}