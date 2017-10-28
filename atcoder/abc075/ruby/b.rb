# 高さ（h）と幅（w）を定義
h, w = gets.split.map(&:to_i)

# 空の配列を定義
squares = []
for i in 0...h
  boxs = gets.chomp.split("")
  squares << boxs
end

def count_mine(squares, h, w, i, j)
  count = 0
  # 探索する座標を時計回りに定義
  h_dir = [-1, -1, 0, 1, 1, 1, 0, -1]
  w_dir = [0, 1, 1, 1, 0, -1, -1, -1]
  DIR_VECTOR = 8
  for k in 0...DIR_VECTOR
    count += exist_mine?(squares, h, w, i, j, h_dir[k], w_dir[k])
  end
  return count
end

# 探索対象の座標にmineが存在するか確認する
def exist_mine?(squares, h, w, i, j, h_dir, w_dir)
  i += h_dir
  j += w_dir
  # 配列外の探索を排除する
  unless i < 0 || w <= j || j < 0 || h <= i
    if squares[i][j] == "#"
      return 1
    end
  end
  return 0
end

# 探索
for i in 0...h
  for j in 0...w
    if squares[i][j]!= "#"
      squares[i][j] = count_mine(squares, h, w, i, j)
    end
  end
end

# 探索の終了した配列を出力する
for i in 0...h
  for j in 0...w
    if j == w - 1
      puts squares[i][j]
    else
      print squares[i][j]
    end
  end
end
