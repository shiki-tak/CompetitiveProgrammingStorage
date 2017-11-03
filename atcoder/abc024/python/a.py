abck = list(map(int, input().split()))
st = list(map(int, input().split()))
fee = abck[0] * st[0] + abck[1] * st[1]
if  abck[3] <= st[0] + st[1]:
    fee = fee - (st[0] + st[1])*abck[2]
print(fee)
