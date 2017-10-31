point = [int(input()) for i in range(3)]
x = sorted(point, reverse=True)
for i in range(len(point)):
    print(x.index(point[i]) + 1)
