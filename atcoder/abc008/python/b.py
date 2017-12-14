from collections import Counter

N = int(input())
candidate = [0 for i in range(N)]
for i in range(N):
    candidate[i] = input()

count_result = Counter(candidate)
result = [0 for i in range(N)]
print(count_result)
for key, value in count_result.items():
    print(key)
    print(value)
