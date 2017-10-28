n, m = gets.split.map(&:to_i)

top_pair = []
for i in 0...m
  pair = gets.chomp.split(&:to_i)
  top_pair << pair
end

# 頂点のペアについて橋になっているか調べる
def check(top_pair, m)
  top_pair = top_pair - check_top(top_pair)
  return top_pair
end

def check_top(top_pair)
  delete_target = []

  # while true
  #   search_target = top_pair.shift
  #   break if !search_target
  #   if search_target[1] == top_pair[j][0]
  #     check_target = [search_target[0], top_pair[j][1]]
  #     if check_target_search(top_pair, check_target) == true
  #       delete_target << search_target << top_pair[j] << check_target
  #     end
  #   end
  # end
  for j in 0...top_pair.length

    while true
      search_target = top_pair.shift
      break if !search_target
      p "s: #{search_target[1]}"
      p "t: #{top_pair[j][0]}"
      p "#{j}"
      p "#{top_pair.shift}"
      p "b(#{j}):#{search_target[1]}"
      p "a(#{j+1}):#{top_pair[j][0]}"
      p "----"

      if search_target[1] == top_pair[j][0]
        check_target = [search_target[0], top_pair[j][1]]
        if check_target_search(top_pair, check_target) == true
          delete_target << search_target << top_pair[j] << check_target
        end
      end
    end
  end
  return delete_target
end

def check_target_search(top_pair, check_target)
  for i in 0...top_pair.length
    # p "#{i}: #{top_pair[i]}"
    # p "#{i}: #{check_target}"
    # p "----"
    if top_pair[i] == check_target
      return true
    end
  end
  return false
end

bridge = check(top_pair, m)
