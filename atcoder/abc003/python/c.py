nk = list(map(int, input().split()))
n = nk[0]
k = nk[1]
c = 0
r = list(map(int, input().split()))
r.sort()
for j in range(k):
    c = (c + r[k-j-1]) / 2
print(int(c))
