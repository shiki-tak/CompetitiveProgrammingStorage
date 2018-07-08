#include <iostream>

using namespace std;

int main() {
  int target[3];
  int countF, countS;
  std::cin >> target[0] >> target[1] >> target[2];

  for (int i = 0; i < 3; i++) {
    if (target[i] == 5) countF++;
    else if (target[i] == 7) countS++;
  }
  if (countF == 2 && countS == 1) std::cout << "YES" << std::endl;
  else std::cout << "NO" << std::endl;

  return 0;
}
