class Checksquareovertargetnumber
  def check_square_over_target_number(search_range, target_array)
    if search_range * search_range <= target_array.length
      return true
    else
      return false
    end
  end
end
