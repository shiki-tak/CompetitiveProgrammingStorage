class Binarysearchmethod
  require './check_input_value.rb'
  require './search.rb'
  target_array = [1, 2, 3, 5, 7, 11, 13, 15, 17, 19, 23]

  @binary = Binarysearchmethod.new()
  @checkinputvale = Checkinputvale.new()
  @search = Search.new()

  puts "****************************"
  puts "*** Start binary Search! ***"
  puts "****************************"
  puts "Input search number"

  puts "#{@search.binary_search(target_array, @checkinputvale.check_input_value(gets.chomp))}"

end
