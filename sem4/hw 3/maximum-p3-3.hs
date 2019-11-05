getFirstIndex :: Eq a => a -> [a] -> Int
getFirstIndex e l = fst $ head $ filter (\a -> snd a == e) $ zip [0..] l

getIndexOfMaxPair :: Ord a => [a] -> Int
getIndexOfMaxPair l = getFirstIndex (maximum l) l

