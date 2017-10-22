class Eratosthenes
  require './check_square_over_target_number.rb'
  @checksquareovertargetnumber = Checksquareovertargetnumber.new()

  def next_index_primary?(search_number, target_array)
    search_number += 1
    while target_array[search_number] == 0
      if target_array[search_number] == 1
        return search_number
      else
        search_number += 1
      end
    end
  end

  def sieve(search_range, target_array)
    search_primary_number = 2
    while search_primary_number < search_range
      i = 2
      while i * search_primary_number <= target_array.length
          target_array[search_primary_number * i] = 0
          i += 1
      end
      next_index, check_result = next_index_primary(search_primary_number, target_array)
      if check_result == true
        next
      else
        return target_array
      end
      # for i in 0...target_array.length
      #   if target_array[i] == 1
      #     puts "#{i}: #{target_array[i]}"
      #   end
      # end
      # for i in 0...target_array.length
      #   target_array[search_primary_number * i] = 0
      #   if target_array[i] == 0
      #     puts "#{i * search_primary_number}: #{target_array[search_primary_number * i]}"
      #   end
      # end

      # if target_array[search_primary_number] == 1
      #   if search_primary_number > search_range
      #     return target_array
      #   end
      # elsif target_array[search_primary_number] == 0
      #   search_primary_number += search_primary_number
      # end
  end
end
