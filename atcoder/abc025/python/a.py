s = input()
n = int(input())
count = 0
flag = False
for i in s:
    for j in s:
        count += 1
        if n == count:
            print(i + j)
            break
