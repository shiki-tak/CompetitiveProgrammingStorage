x = list(map(int, input().split()))
a = (x[0] + 1) * x[1]
b = (x[1] + 1) * x[0]
if a < b:
    print(b)
else:
    print(a)
