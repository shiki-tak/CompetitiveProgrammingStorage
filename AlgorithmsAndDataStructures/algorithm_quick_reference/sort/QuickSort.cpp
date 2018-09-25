#include <iostream>
#include <vector>

using namespace std;

void do_qsort(vector<int>& array, int begin, int end)
{
    
    int i = begin;
    int j = end;
    int pivot = array[(begin + end) / 2]; // 中央の値をピボットにする

    while (true) {
        while (array[i] < pivot) { i++; }
        while (array[j] > pivot) { j--; }        
        if (i >= j) { break; }

    // 入れ替え
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
    }
    // 軸の左側をソートする
    if( begin < i - 1 ) { do_qsort(array, begin, i - 1 ); }
    if (j + 1 < end) { do_qsort(array, j + 1, end); }
}

int main()
{
    vector<int> array;

    int n; cin >> n;

    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        array.push_back(x);
    }

    do_qsort(array, 0, n - 1);

    for (int i = 0; i < n; i++) {
        if (i == array.size() - 1) cout << array[i] << endl;
        else cout << array[i] << " ";
    }

    return 0;
}