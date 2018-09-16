#include <iostream>
#include <vector>

using namespace std;

struct Bucket {
    vector<int> b;
};

void sort() {

}

int main()
{
    struct Bucket buckets[100];

    int n; cin >> n;
    int a[100];
    for (int i = 0; i < n; i++) cin >> a[i];

    int maxK = 0;
    for (int i = 0; i < n; i++) {
        int k = a[i] / 3;
        if (k > maxK) maxK = k;
        buckets[k].b.push_back(a[i]);
    }
    
    for (int i = 0; i <= maxK; i++) {
        if (buckets[i].b.size() > 1) sort(buckets[i].b.begin(), buckets[i].b.end());
    }

    for (int i = 0; i <= maxK; i++) {
        for (int j = 0; j < buckets[i].b.size(); j++) {
            if (i == maxK && j == buckets[i].b.size() - 1) cout << buckets[i].b[j] << endl;
            else                                           cout << buckets[i].b[j] << " ";
        }
    }

    return 0;
}