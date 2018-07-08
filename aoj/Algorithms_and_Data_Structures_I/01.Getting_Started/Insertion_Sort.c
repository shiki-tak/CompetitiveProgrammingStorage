#include <stdio.h>

void insertSort(int A[], int N) {
  for (int i = 1; i < N; i++) {
    int v;
    v = A[i];
    int j = i -1;
    while (j >= 0 && A[j] > v) {
      A[j + 1] = A[j];
      A[j--] = v;
    }
    for (int j = 0; j < N; j++) {
      if (j == N - 1) {
        printf("%d", A[N-1]);
      } else {
        printf("%d ", A[j]);
      }
    }
    printf("\n");
  }
}

int main() {
  int N;
  scanf("%d", &N);
  int A[N];

  for (int i = 0; i < N; i++) {
    scanf("%d", &A[i]);
  }
  for (int j = 0; j < N; j++) {
    if (j == N - 1) {
      printf("%d", A[N-1]);
    } else {
      printf("%d ", A[j]);
    }
  }
  printf("\n");

  insertSort(A, N);

  return 0;
}
