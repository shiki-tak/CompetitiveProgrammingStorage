class Checkinputvale
  def check_input_value(target_number)
    if target_number =~ /^[0-9]+$/
      return target_number.to_i
    else
      puts "Please input number"
      exit
    end
  end
end
