#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
  int key;
  struct Node *next, *prev;
};

struct Node *nil;

// リストの初期化
void init() {
  nil = (struct Node *)malloc(sizeof(struct Node));
  nil->prev = nil;
  nil->next = nil;
}

void insert(int key) {
  struct Node *x = (struct Node *)malloc(sizeof(struct Node));
  x -> key = key;
  x -> next = nil -> next;
  nil -> next -> prev = x;
  nil -> next = x;
  x -> prev = nil;
}

void deleteNode(struct Node *t) {
  if (t == nil) return;
  t -> prev -> next = t -> next;
  t -> next -> prev = t -> prev;
  free(t);
}

struct Node* listSearch(int key) {
  struct Node *cur = nil -> next;
  while( cur != nil && cur -> key != key) {
    cur = cur -> next;
  }
  return cur;
}
void delete(int key) {
  deleteNode(listSearch(key));
}

void deleteFirst() {
  deleteNode(nil -> next);
}

void deleteLast() {
  deleteNode(nil -> prev);
}

void printList() {
  struct Node *cur = nil -> next;
  // 最初のリスト
  while(cur) {
    printf("%d", cur -> key);
    cur = cur -> next;
    if (cur == nil){
      break;
    } else {
      printf(" ");
    }
  }
  printf("\n");
}

int main() {
  int n;
  char com[15];
  int key;
  scanf("%d\n", &n);
  init();
  for (int i = 0; i < n; i++) {
    scanf("%s%d", com, &key);
    if (com[0] == 'i') {
      insert(key);
    } else if (com[0] == 'd') {
      if (strlen(com) > 6) {
        if (com[6] == 'F') deleteFirst();
        else if (com[6] == 'L') deleteLast();
      } else {
        delete(key);
      }
    }
  }
  printList();
  return 0;
}
