before_board = []
after_board = []

for i in range(4):
    init_values = [0, 0, 0, 0]
    after_board.append(init_values)

for i in range(4):
    values = list(input().split())
    before_board.append(values)

for i in range(4):
    for j in range(4):
        after_board[i][j] = before_board[3 - i][3 - j]

for i in range(4):
    for j in range(4):
        if j == 3:
            print(after_board[i][j])
        else:
            print(after_board[i][j], '', end='')
