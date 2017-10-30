x = list(map(int, input().split()))
y = list(map(int, input().split()))
z = list(map(int, input().split()))
point_list = [x, y, z]
result = 0
for i in range(3):
    result += point_list[i][0] * point_list[i][1] / 10
print(int(result))
