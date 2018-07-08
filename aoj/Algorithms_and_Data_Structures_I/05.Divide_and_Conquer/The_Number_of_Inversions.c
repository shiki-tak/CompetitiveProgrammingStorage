#include <stdio.h>

long cnt = 0;

void merge(int a[], int left, int mid, int right) {
  int n1 = mid - left;
  int n2 = right - mid;
  int L[2000000];
  int R[2000000];
  for (int i = 0; i < n1; i++) {
    L[i] = a[left + i];
  }
  for (int i = 0; i < n2; i++) {
    R[i] = a[mid + i];
  }
  L[n1] = 1000000000;
  R[n2] = 1000000000;

  int i = 0;
  int j = 0;
  for (int k = left; k < right; k++) {
    if (L[i] <= R[j]) {
      a[k] = L[i++];
    } else {
      a[k] = R[j++];
      cnt += n1 - i;
    }
  }
}

void mergeSort(int a[], int left, int right) {
  int mid = 0;
  mid = (left + right) / 2;
  if (left + 1 < right) {
    mergeSort(a, left, mid);
    mergeSort(a, mid, right);
    merge(a, left, mid, right);
  }
}

int main() {
  int n;
  scanf("%d\n", &n);
  int a[n];
  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  mergeSort(a, 0, n);
  printf("%ld\n", cnt);

  return 0;
}
