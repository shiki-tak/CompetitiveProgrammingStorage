#include <iostream>
#include <string>
#include <cmath>

using namespace std;

string s;
int a[10];


int sumNum(int sum) {
    // i個の+を間に入れる
    for (int i = 1; i <= s.length() - 1; i++) {
        int n = 0;
        
        for (int j = i; j > 0; j--) {
            n += (s[j] - '0') * pow(10, j);
        }


        cout << n << endl;
    }
    
    return sum;
}

int main()
{
    cin >> s;
    for (int i = 0; i < s.length(); i++) a[i] = s[i] - '0';

    sumNum(stoi(s));


    return 0;
}