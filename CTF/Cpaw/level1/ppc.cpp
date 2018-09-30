#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int a[] = {15,1,93,52,66,31,87,0,42,77,46,24,99,10,19,36,27,4,58,76,2,81,50,102,33,94,20,14,80,82,49,41,12,143,121,7,111,100,60,55,108,34,150,103,109,130,25,54,57,159,136,110,3,167,119,72,18,151,105,171,160,144,85,201,193,188,190,146,210,211,63,207};
    vector<int> target;
    for (int i = 0; i < sizeof(a) / sizeof(*a); i++) target.push_back(a[i]);
    sort(target.begin(), target.end(), greater<int>());

    cout << "cpaw{";
    for (int i = 0; i < target.size(); i++) cout << target[i];
    cout << "}" << endl;

    return 0;
}