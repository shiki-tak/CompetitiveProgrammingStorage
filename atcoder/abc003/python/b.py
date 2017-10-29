s_t_list = []
for i in range(2):
    s_t_list.append(input())
atcoder = 'atcoder'
result = 'You can win'
for i in range(len(s_t_list[0])):
    s = s_t_list[0][i]
    t = s_t_list[1][i]
    if s != t:
        # どちらも@でなければ不一致
        if s != '@' and t != '@':
            result = 'You will lose'
            break
        if s != '@':
            if (s in atcoder) == False:
                result = 'You will lose'
                break
        if t != '@':
            if (t in atcoder) == False:
                result = 'You will lose'
                break

print(result)
