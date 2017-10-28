# ボールの数とロボットBのx座標を定義
n = gets.chomp.to_i
k = gets.chomp.to_i

# ボールを配置するx座標を定義
ball_dir = gets.split.map(&:to_i)

# 探索
def start_calc(ball_dir, k, n)
  count_result = 0
  for i in 0...n
    count_result += calc_r_to_b_distance(ball_dir[i], k)
  end
  return count_result
end

# 各y座標上にあるボールとロボットの距離を計算する
def calc_r_to_b_distance(ball_dir, k)
  rA_to_ball = ball_dir
  rB_to_ball = k - ball_dir
  if rA_to_ball <= rB_to_ball
    count = rA_to_ball
  else
    count = rB_to_ball
  end
  return count * 2
end

# 探索結果を出力
p start_calc(ball_dir, k, n)
