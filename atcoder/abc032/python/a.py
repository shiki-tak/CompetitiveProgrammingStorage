x = [int(input()) for i in range(3)]
while True:
    if x[2] % x[1] == 0 and x[2] % x[0] == 0:
        print(x[2])
        break
    else:
        x[2] += 1
