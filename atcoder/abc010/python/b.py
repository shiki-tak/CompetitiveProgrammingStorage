x = int(input())
a = list(map(int, input().split()))
count = 0

for i in range(x):
    if a[i] == 6:
        count += 3
    elif a[i] == 5:
        count += 2
    elif a[i] == 2 or a[i] == 4 or a[i] == 8:
        count += 1

print(count)
