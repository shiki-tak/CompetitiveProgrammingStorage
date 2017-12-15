N = int(input())
price = []

for i in range(N):
    price.append(int(input()))

result = list(set(price))
sorted_result = sorted(result, reverse=True)
print(sorted_result[1])
