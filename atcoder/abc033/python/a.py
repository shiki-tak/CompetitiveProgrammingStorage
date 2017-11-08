n = input()
v = n[0]
flag = True
for i in range(len(n)):
    if v != n[i]:
        flag = False

if flag:
    print("SAME")
else:
    print("DIFFERENT")
