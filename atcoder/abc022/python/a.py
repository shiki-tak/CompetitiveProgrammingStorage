nst = list(map(int, input().split()))
n = nst[0]
s = nst[1]
t = nst[2]
a = [int(input()) for i in range(n)]

count = 0
w = 0

for i in range(n):
    w += a[i]
    if s <= w <=  t:
        count += 1

print(count)
