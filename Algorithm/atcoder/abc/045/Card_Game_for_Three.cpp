#include <iostream>
#include <string>

using namespace std;

using std::cin;
using std::cout;
using std::endl;

// カードを格納
string s[3];

// A, B, Cのカードの配列の位置
int ap, bp, cp;

int game(int participant) {
  if (participant == 0) {
    ap++;
    // 勝利条件
    if (!s[participant][ap]) {
      return participant;
    } else {
      if (s[participant][ap] == 'a') {
        return game(0);
      } else if (s[participant][ap] == 'b') {
        return game(1);
      } else {
        return game(2);
      }
    }
  } else if (participant == 1) {
    bp++;
    // 勝利条件
    if (!s[participant][bp]) {
      return participant;
    } else {
      if (s[participant][bp] == 'a') {
        return game(0);
      } else if (s[participant][bp] == 'b') {
        return game(1);
      } else {
        return game(2);
      }
    }
  } else {
    cp++;
    // 勝利条件
    if (!s[participant][cp]) {
      return participant;
    } else {
      if (s[participant][cp] == 'a') {
        return game(0);
      } else if (s[participant][cp] == 'b') {
        return game(1);
      } else {
        return game(2);
      }
    }
  }
}

int main() {
  // 勝った人を表す
  int win;
  // カードのイテレータの初期値
  ap = -1;
  bp = -1;
  cp = -1;

  // 手札の入力
  for (int i = 0; i < 3; i++) {
    cin >> s[i];
  }

  /* 勝者を判定する
  　　参加者は0, 1, 2で表す
  　　A -> 0
  　　B -> 1
  　　C -> 2
  */
  ap++; // ゲーム開始
  win = game(0);

  // 出力
  switch (win) {
    case  0:
      cout << "A" << endl;
      break;
    case  1:
      cout << "B" << endl;
      break;
    case  2:
      cout << "C" << endl;
      break;
  }
  return 0;
}
