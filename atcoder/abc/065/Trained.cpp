#include <iostream>

using namespace std;

int a[100001] = {0};
int visited[100001] = {0};

int canFinishTraining(int x, int ret) {
    if (x == 2) return ret;
    ret++;

    if (visited[x] == 1) {
        ret = -1;
        return ret;
    } else {
        visited[x] = 1;
        return canFinishTraining(a[x], ret);
    }
}

int main()
{
    int n; cin >> n;

    for (int i = 1; i <= n; i++) cin >> a[i];

    // 判定
    cout << canFinishTraining(1, 0) << endl;

    return 0;
}