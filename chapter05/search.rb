class Search
  require './compare_number.rb'
  require './compare_head_tail_index.rb'

  def binary_search(target_array, target_number)
    @comparenumber = Comparenumber.new()
    @compareheadtailindex = Compareheadtailindex.new()

    head = 0
    tail = target_array.length
    center = 0

    for i in 0...target_array.length
      center = (head + tail) / 2
      compare_result = @comparenumber.compare_number(target_array[center], target_number)
      if compare_result == 0
        return "#{target_number} found!"
      elsif compare_result == 1
        tail = center - 1
      else
        head = center + 1
      end
      if @compareheadtailindex.compare_head_tail_index(head, tail) == false
        return "#{target_number} not found..."
      end
    end
  end
end
