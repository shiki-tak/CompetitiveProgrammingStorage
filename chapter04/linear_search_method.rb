class Linearsearch
  @linear = Linearsearch.new()

  def check_input_value(check_value)
    if check_value =~ /^[0-9]+$/
      return check_value.to_i
    else
      puts "Please input number"
      exit
    end
  end

  def double_check(target_array, target)
    for i in 0...target_array.length
      if target_array[i] == target
        return false
      end
    end
    return true
  end

  def set_target_array_length
    puts "Input target array.length"
    array_length = gets.chomp
    check_input_value(array_length)
  end

  def create_target_array(rand_number_range, array_length)
    @target_array = []
    for i in 0..array_length
      if i == 0
        @target_array.push(rand(1...rand_number_range))
        next
      end
        puts "#{@target_array}"
        target_number = rand(1...rand_number_range)
        # double = @linear.double_check(@target_array, target_number)
        @target_array.push(target_number)
    end
    return @target_array
  end

  def linear_search(target_array, target_number)
    for i in 0...target_array.length
      if target_number == target_array[i]
        return puts "#{target_number} found!"
      end
    end
    return puts "#{target_number} not found..."
  end

  puts "****************************"
  puts "*** Start Linear Search! ***"
  puts "****************************"
  puts "Input array number range"
  rand_number = @linear.check_input_value(gets.chomp)
  target_array_result = @linear.set_target_array_length
  if rand_number > target_array_result
    puts "The range of numbers must be greater than the length of the array"
    exit
  else
    target_array_result = @linear.create_target_array(rand_number, target_array_result)
  end
  puts "Please input search number"
  puts "(The number stored in the array is one of 1 to #{rand_number})"
  @linear.linear_search(target_array_result, @linear.check_input_value(gets.chomp))
end
