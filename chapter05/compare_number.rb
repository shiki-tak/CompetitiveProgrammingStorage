class Comparenumber
  def compare_number(array_number, target_number)
    if array_number == target_number
      return 0
    elsif array_number > target_number
      return 1
    else
      return 2
    end
  end
end
