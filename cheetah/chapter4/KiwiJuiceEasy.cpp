#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int main()
{
    int n, pourNumber; cin >> n >> pourNumber;

    // ボトルの容量
    vector<int> capacity;
    for (int i = 0; i <n; i++) {
        int x; cin >> x;
        capacity.push_back(x);
    }

    // 実際に入っている量
    vector<int> bottles;
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        bottles.push_back(x);
    }

    // 移動元、移動先のId
    int fromId[pourNumber];
    int toId[pourNumber];

    // 移動元のIdを指定
    for (int i = 0; i < pourNumber; i++) cin >> fromId[i];

    // 移動先のIdを指定
    for (int i = 0; i < pourNumber; i++) cin >> toId[i];

    // ジュースを移動した後の実際に入っている量を計算する
    for (int i = 0; i < pourNumber; i++) {
        // 移動元と移動先のジュースの量を計算する
        // toIdBottlesとfromIdBottlesの合計を計算
        int sum = bottles[toId[i]] + bottles[fromId[i]];

        // 少ない方を取得
        bottles[toId[i]] = min(sum, capacity[toId[i]]);
        bottles[fromId[i]] = sum - bottles[toId[i]];
    }
    
    // 出力
    for (int x : bottles) cout << x << endl;
  
    return 0;
}