n = int(input())
tribonacci = [0, 0, 1]
for i in range(3,n+1):
    tribonacci.append(tribonacci[i - 1] + tribonacci[i - 2] + tribonacci[i - 3])
print(tribonacci[n-1] % 10007)
