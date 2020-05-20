#include <iostream>

using namespace std;

int p[11] = {0};
int c[11] = {0};

int calcScore(int score, int t, int goal) {

    if (score >= goal) {
        return t;
    } else {
        
    }
}

int main()
{
    int d, g; cin >> d >> g;

    for (int i = 1; i <= d; i++) {
        cin >> p[i] >> c[i];
    }

    calcScore(0, 0, g);
    return 0;
}