cylinder :: Double -> Double -> Double
cylinder r h =
  let sideArea = 2 * pi * h
      topArea = pi * r ^ 2
  in  sideArea + 2 * topArea
