class Eratosthenes
  require './check_square_over_target_number.rb'


  def index_primary?(search_number, target_array)
    search_number += 1
    while check_array_number(search_number, target_array) == false
      search_number += 1
    end
    return search_number
  end

  def check_array_number(check_array_number, target_array)
    if target_array[check_array_number] == 0
      return true
    else
      return false
    end
  end

  def sieve(target_array)
    @checksquareovertargetnumber = Checksquareovertargetnumber.new()
    search_primary_number = 2
    puts "#{search_primary_number}"
    puts "#{target_array}"
    while @checksquareovertargetnumber.check_square_over_target_number(search_primary_number, target_array) == true
      i = 2
      while i * search_primary_number <= target_array.length
          target_array[search_primary_number * i] = 0
          i += 1
      end
      search_primary_number = index_primary?(search_primary_number, target_array)
      for i in 0...target_array.length
        puts "#{i}: #{target_array[i]}"
      end
    end
  end
end
