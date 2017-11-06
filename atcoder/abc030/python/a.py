x = list(map(int, input().split()))
if x[1] / x[0] < x[3] / x[2]:
    print('AOKI')
elif x[3] / x[2] < x[1] / x[0]:
    print('TAKAHASHI')
else:
    print('DRAW')
