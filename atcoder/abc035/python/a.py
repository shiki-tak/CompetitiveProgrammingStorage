x = list(map(int, input().split()))
if x[0] % 16 == 0 and x[1] % 9 == 0:
    print('16:9')
else:
    print('4:3')
