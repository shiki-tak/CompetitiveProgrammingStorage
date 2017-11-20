x = list(map(int, input().split()))
y = x[1] / x[0]
z = x[1] % x[0]
if z == 0:
    print(int(y))
else:
    print(int(y + 1))
