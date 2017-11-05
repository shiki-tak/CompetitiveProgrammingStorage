s = list(input())
n = int(input())
x = ''
count = 1
flag = False
for i in range(4):
    for j in range(4):
        x = s[i] + s[j]
        count += 1
        if n == count:
            flag = True
            break
    if flag == True:
        break
print(x)
