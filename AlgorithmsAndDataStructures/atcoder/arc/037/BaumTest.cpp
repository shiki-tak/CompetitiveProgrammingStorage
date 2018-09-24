#include <iostream>
#include <vector>

using namespace std;

int n = 0, m = 0;
vector<int> G[110];
int checkAry[110] = { 0 };
bool flag;

void dfs(int c, int p) {

    for (int next : G[c]) {
        if (next == p) continue;
        if (checkAry[next] == 1) {
            flag = false;
        } else {
            checkAry[next] = 1;
            dfs(next, c);
        }
    }
    return;
}

int check() {
    int cnt = 0;

    for (int i = 0; i < n; i++) {
        if (checkAry[i] == 0) {
            flag = true;
            dfs(i, -1);
            if (flag) cnt++;
        }
    }
    return cnt;
}

int main()
{
    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        int ui, vi; cin >> ui >> vi;
        ui--;
        vi--;
        G[ui].push_back(vi);
        G[vi].push_back(ui);
    }

    cout << check() << endl;

    return 0;
}