class Initarray
  def init_array(array_range, target_array)
    for i in 0..array_range
      target_array[i] = 1
    end
    return target_array
  end
end
