def eratosthenes(number)
  numbers = (2..number).to_a
  primes = []
  while true
    target = numbers.shift
    break if !target
    primes << target
    to_delete = []
    numbers.each do |x|
      to_delete << x if x % target == 0
    end
    numbers = numbers - to_delete
  end
  return primes
end

p eratosthenes(100)
