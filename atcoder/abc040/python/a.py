x = list(map(int, input().split()))
y = x[0] - x[1]
if x[1] == 1 or x[1] == x[0]:
    print(0)
elif x[1] <= y:
    print(x[1] - 1)
else:
    print(y)
