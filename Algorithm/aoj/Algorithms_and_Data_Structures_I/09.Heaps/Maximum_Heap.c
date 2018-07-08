#include <stdio.h>
#define MAX 2000000

int n, A[MAX + 1];

void maxHeapify(int i) {
  int l, r, largest;
  l = 2 * i;
  r = 2 * i + 1;

  // 左の子、自分、右の子で値が最大のノードを選ぶ
  if(l <= n && A[l] > A[i]) largest = l;
  else largest = i;
  if (r <= n && A[r] > A[largest]) largest = r;

  if (largest != i) {
    //swap
    int x = A[i];
    A[i] = A[largest];
    A[largest] = x;
    maxHeapify(largest);
  }

}

int main() {
  scanf("%d", &n);
  for (int i = 1; i <= n; i++) {
    scanf("%d", &A[i]);
  }

  for (int i = n / 2; i >= 1; i--) {
    maxHeapify(i);
  }

  for (int i = 1; i <= n; i++) {
    printf(" %d", A[i]);
  }
  printf("\n");

  return 0;
}
