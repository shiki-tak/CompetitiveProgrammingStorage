#include <stdio.h>
#include <stdlib.h>

#define SENTINEL 1000000000

int cnt = 0;

void merge(int a[], int left, int mid, int right) {
  int n1 = mid - left;
  int n2 = right - mid;
  int *L;
  int *R;
  L = (int *)malloc(sizeof(int)*(n1 + 1));
  R = (int *)malloc(sizeof(int)*(n2 + 1));
  for (int i = 0; i < n1; i++) {
    L[i] = a[left + i];
  }
  for (int i = 0; i < n2; i++) {
    R[i] = a[mid + i];
  }
  L[n1] = SENTINEL;
  R[n2] = SENTINEL;
  int i = 0;
  int j = 0;
  for (int k = left; k < right; k++) {
    if (L[i] <= R[j]) {
      a[k] = L[i];
      i++;
    } else {
      a[k] = R[j];
      j++;
    }
    cnt++;
  }
  free(L);
  free(R);
}

void mergeSort(int a[], int left, int right) {
  int mid = 0;
  if (left + 1 < right) {
    mid = (left + right) / 2;
    mergeSort(a, left, mid);
    mergeSort(a, mid, right);
    merge(a, left, mid, right);
  }
}

int main() {
  int n;
  scanf("%d", &n);
  int a[500000];

  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  mergeSort(a, 0, n);

  for (int i = 0; i < n; i++) {
    if (i == n - 1) {
      printf("%d", a[n - 1]);
    } else {
      printf("%d ", a[i]);
    }
  }
  printf("\n");
  printf("%d\n", cnt);

  return 0;
}
