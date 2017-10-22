class Eratosthenessieve
  require 'complex'
  require "../chapter05/check_input_value.rb"
  require "./init_array.rb"
  require "./eratosthenes.rb"


  @checkinputvalue = Checkinputvale.new()
  @initarray = Initarray.new()
  @sieve = Eratosthenes.new()
  target_array = []

  puts "*********************************"
  puts "*** Start Eratosthenes sieve! ***"
  puts "*********************************"
  puts "Input search range"
  array_range = @checkinputvalue.check_input_value(gets.chomp)
  search_range = Math.sqrt(array_range)
  target_array = @initarray.init_array(array_range, target_array)
  # puts "#{target_array}"
  @sieve.sieve(search_range, target_array)
end
