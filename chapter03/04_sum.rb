class Sum
  sum = Sum.new()
  # Enter the number to calculate
  def numberInput
    puts " Input 'end' after entering the number"
    sum_target = []
    number = gets.chomp
    while number =~ /^[0-9]+$/ do
      sum_target.push(number.to_i)
      number = gets.chomp
    end
    return sum_target
  end

  def sumCal(sum_target)
    sum_result = 0
    for i in 0...sum_target.length
      sum_result += sum_target[i]
    end
    return sum_result
  end

  puts "Input target numbers"
  sum_target = sum.numberInput
  result = sum.sumCal(sum_target)
  puts "Result: #{result}"
end
