w = list(input())
result = []
for char in w:
    if char != 'a' and char != 'i' and char != 'u' and char != 'e' and char != 'o':
        result.append(char)
print(''.join(list(result)))
