getMatrix :: Integer -> [[Integer]]
getMatrix n = map(\a -> (sumPairLists [1..n].reverse) [0..a-1]) [1..n]
-- 2.5
sumPairLists :: [Integer] -> [Integer] -> [Integer]
sumPairLists [] list = list
sumPairLists list [] = list
sumPairLists (head1:tail1) (head2:tail2) = head1 + head2 : sumPairLists tail1 tail2