#include <stdio.h>
#include <stdlib.h>

#define STACK_SIZE 1000
#define MAX_LENGTH 1000

int stack[STACK_SIZE];
int stack_size = 0;

int pop() {
  if (stack_size <= 0) {
    return 0;
  }
  return stack[--stack_size];
}

void push(int x) {
  stack[stack_size++] = x;
}

int check_number(char *s) {
  // 符号あり
  if (*s == '+' || *s == '-') {
    s++;
    if (*s >= '0' && *s <= '9') {
      return 1;
    } else {
      return 0;
    }
  }
  // 符号なし
  if (*s >= '0' && *s <= '9') {
    return 1;
  } else {
    return 0;
  }
}

int get_number(char *s) {
  int sign = 0;
  int n = 0;

  if (*s == '+') {
    s++;
  } else if (*s == '-') {
    sign = 1;
    s++;
  }
  while(*s >= '0' && *s <= '9') {
    n = n * 10 + (*s - '0');
    s++;
  }
  if (sign) {
    return -n;
  } else {
    return n;
  }
}

int calc(char *s) {
  int x, y, n;

  while(*s) {
    if (check_number(s)) {
      n = get_number(s);
      push(n);
      while (*s == '+' || *s == '-') {
        s++;
      }
      while (n) {
        n /= 10;
        s++;
      }
    } else {
      switch (*s) {
        case '+':
          x = pop();
          y = pop();
          push(y+x);
          break;
        case '-':
          x = pop();
          y = pop();
          push(y-x);
          break;
        case '*':
          x = pop();
          y = pop();
          push(y*x);
          break;
      }
      s++;
    }
  }
  return pop();
}

int main() {
  char s[MAX_LENGTH];
  scanf("%[^\n]*s", s);
	printf("%d\n", calc(s));

  return 0;
}
