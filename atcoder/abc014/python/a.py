a = int(input())
b = int(input())
for i in range(b):
    if (a + i) % b == 0:
        print(i)
        break
