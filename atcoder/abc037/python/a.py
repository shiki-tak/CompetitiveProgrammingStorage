x = list(map(int, input().split()))
if x[0] < x[1]:
    ans = x[2] // x[0]
else:
    ans = x[2] // x[1]
print(ans)
