values = list(map(float, input().split()))
a = [values[0], values[1]]
b = [values[2], values[3]]
c = [values[4], values[5]]
if a[0] != 0:
    b[0] -= a[0]
    c[0] -= a[0]
if a[1] != 0:
    b[1] -= a[1]
    c[1] -= a[1]
s = abs(b[0] * c[1] - b[1] * c[0]) / 2
print(s)
