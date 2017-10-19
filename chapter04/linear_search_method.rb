class Linearsearch
  linear = Linearsearch.new()
  target_array = [3, 9, 10, 62, 31]

  def linear_search(target_array, target_number)
    for i in 0...target_array.length
      if target_number == target_array[i]
        return puts "#{target_number} found!"
      end
    end
    return puts "#{target_number} not found..."
  end


  puts "Start Linear Search!"
  puts "Please input search number"
  target_number = gets.chomp
  if target_number =~ /^[0-9]+$/
    linear.linear_search(target_array, target_number.to_i)
  end
end
