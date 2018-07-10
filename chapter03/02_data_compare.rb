# Modified to algorithm to compare arbitrary number of numbers
class Datacompare
  # create instance
  datacompare = Datacompare.new()

 # The numerical value of the input character is checked
  def check_number_type(target_number)
    if target_number =~ /^[0-9]+$/
      return true
    else
      return false
    end
  end

  # Compare numbers
  def compare(compare_number_set)
    max_number = 0
    for i in 1...compare_number_set.length
      if i == 1
        if compare_number_set[i-1] < compare_number_set[i]
          max_number = compare_number_set[i]
        else
          max_number = compare_number_set[i-1]
        end
      else
        # puts "now max: #{max_number}"
        if max_number < compare_number_set[i]
          max_number = compare_number_set[i]
        end
      end
    end
    return max_number
  end

  # Compare Start
  puts "**********************"
  puts "*** Compare Start! ***"
  puts "**********************"
  puts "How many figures do you compare?"
  # Array to place numbers to compare
  compare_number_set = []
  # Number of digits to compare
  compare_figure = gets.chomp
  if datacompare.check_number_type(compare_figure) == false
    puts "Please input Integer number"
    exit
  end
  figure = compare_figure.to_i

  for i in 1..figure
    puts "Input #{i} number"
    target = gets.chomp
    if datacompare.check_number_type(target) == true
      target_number = target.to_i
      compare_number_set.push(target_number)
    else
      puts "Please input Integer number"
      exit
    end
  end

  puts "Max number is #{datacompare.compare(compare_number_set)}"
end
