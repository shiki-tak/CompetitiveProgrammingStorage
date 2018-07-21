#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int ret = 0;
    vector<int> tasks;

    for (int i = 0; i < 3; i++) {
        int x; cin >> x;
        tasks.push_back(x);
    }
    sort(tasks.begin(), tasks.end());

    for (int i = 1; i < 3; i++) ret += tasks[i] - tasks[i - 1];

    cout << ret << endl;

    return 0;
}