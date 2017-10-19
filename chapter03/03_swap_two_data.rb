class Swaptwodata
  datas = []
  puts "******************"
  puts "*** Start Swap ***"
  puts "******************"
  puts "Input First data"
  data1 = gets.chomp
  datas.push(data1)
  puts "Input Second data"
  data2 = gets.chomp
  datas.push(data2)
  puts "Before: #{datas}"
  dummy = datas[0]
  datas[0] = datas[1]
  datas[1] = dummy
  puts "After: #{datas}"
end
