#include <stdio.h>

// プロセスを表す構造体
typedef struct {
  char name[100];
  int t;
} P;

P Q[100000];
int head, tail, n, q, c;

void enqueue(P x) {
  Q[tail] = x;
  if (tail == n) {
    tail = 0;
  } else {
    tail++;
  }
}

P dequeue() {
  P x = Q[head];
  if (head == n) {
    head = 0;
  } else {
    head++;
  }
  return x;
}

int min(int a, int b) {
  if (a <= b) {
    return a;
  } else {
    return b;
  }
}

int main() {
  // 経過時間
  int elapsed = 0;

  scanf("%d %d", &n, &q);
  P u;

  for (int i = 0; i < n; i++) {
    scanf("%s", Q[i].name);
    scanf("%d", &Q[i].t);
  }
  head = 0;
  tail = n;

  while(head != tail) {
    u = dequeue();
    c = min(q, u.t);
    u.t -= c;
    elapsed += c;
    if (u.t > 0) {
      enqueue(u);
    } else {
      printf("%s %d\n", u.name, elapsed);
    }
  }
  return 0;
}
