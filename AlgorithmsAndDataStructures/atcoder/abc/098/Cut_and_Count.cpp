#include <iostream>
#include <string>
#include <vector>
#include <cmath>

int main()
{
    int n; std::cin >> n;
    int res = 0;
    std::string s; std::cin >> s;
    std::vector<char> us;

    // Cut後にCountする文字を格納する
    for (char c : s) {
        if (std::count(us.begin(), us.end(), c) == 0) us.push_back(c);
    }

    for (int i = 1; i <= n; i++) {
        int cnt = 0;
        for (char c : us) {
            if (std::count(s.begin(), s.begin() + i, c) != 0 && std::count(s.begin() + i, s.end(), c) != 0) cnt++;
        }
        res = std::max(cnt, res);
    }

    std::cout << res << std::endl;

    return 0;
}