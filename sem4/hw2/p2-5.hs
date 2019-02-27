sumPairLists :: [Integer] -> [Integer] -> [Integer]
sumPairLists [] list = list
sumPairLists list [] = list
sumPairLists (head1:tail1) (head2:tail2) = head1 + head2 : sumPairLists tail1 tail2

sumLists :: [Integer] -> [Integer] -> [Integer] -> [Integer]
sumLists a b c = sumPairLists (sumPairLists a b) c