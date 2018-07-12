rightTriangles x len = [(a, b, c) | c <- [1..x], a <- [1..c], b <- [1..a], a^2 + b^2 == c^2, a + b + c == len]
