sumOfNumbers :: Integer -> Integer
sumOfNumbers 0 = 0
sumOfNumbers n = mod n 10 + sumOfNumbers (div n 10)