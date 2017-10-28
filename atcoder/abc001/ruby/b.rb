m = gets.chomp.to_i
km_m = m / 1000.0

case km_m
when 0...0.1
  puts "00"
when 0.1..5
  vv = km_m * 10
  if vv < 10
    vv = "0" + vv.to_i.to_s
    puts vv
  else
    puts vv.to_i
  end
when 6..30
  vv = km_m + 50
  puts vv.to_i
when 35..70
  vv = (km_m - 30)/5 + 80
  puts vv.to_i
else
  puts 89
end
