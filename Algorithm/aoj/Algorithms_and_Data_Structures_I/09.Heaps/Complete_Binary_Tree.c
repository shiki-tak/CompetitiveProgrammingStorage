#include <stdio.h>
#define MAX 100000

int parent(int i) { return i / 2; }
int left(int i) { return 2 * i; }
int right(int i) { return 2 * i + 1; }

int main() {
  int n;
  int A[MAX+1];

  scanf("%d", &n);
  for (int i = 1; i <= n; i++) {
    scanf("%d", &A[i]);
  }

  for (int i = 1; i <=n; i++) {
    printf("node %d: key = %d, ", i, A[i]);
    if (parent(i) >= 1) {
      printf("parent key = %d, ", A[parent(i)]);
    }
    if (left(i) <= n) {
      printf("left key = %d, ", A[left(i)]);
    }
    if (right(i) <= n) {
      printf("right key = %d, ", A[right(i)]);
    }
    printf("\n");
  }

  return 0;
}
