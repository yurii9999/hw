firstEntryIndex :: Integer -> [Integer] -> Integer
firstEntryIndex _ [] = -1
firstEntryIndex e (head:tail) = if e == head
                                then 0
                                else previous (firstEntryIndex e tail)

previous :: Integer -> Integer
previous (-1) = -1
previous n = n + 1

a = [1, 4, 6, 4, 1, 7, 6]