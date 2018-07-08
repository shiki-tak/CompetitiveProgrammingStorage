#include <iostream>
#include <string>

using std::cin;
using std::cout;
using std::endl;

// カードを格納
string s[3];

// A, B, Cのカードの配列の位置
// カードのイテレータの初期値
int ap = -1;
int bp = -1;
int cp = -1;

int game(int participant) {
  if (participant == 0) {
    ap++;
    // 勝利条件
    if (!s[participant][ap]) return participant;
    else                     return game(s[participant][ap] - 97);
  } else if (participant == 1) {
    bp++;
    // 勝利条件
    if (!s[participant][bp]) return participant;
    else                     return game(s[participant][bp] - 97);
  } else {
    cp++;
    // 勝利条件
    if (!s[participant][cp]) return participant;
    else                     return game(s[participant][cp] - 97);
  }
}

int main() {
  // 勝った人を表す
  int win;

  // 手札の入力
  for (int i = 0; i < 3; i++) cin >> s[i];

  /* 勝者を判定する
  　　参加者は0, 1, 2で表す
  　　A -> 0
  　　B -> 1
  　　C -> 2
  */
  ap++; // ゲーム開始
  win = game(0);
  char winner = win + 65;

  cout << winner << endl;

  return 0;
}
