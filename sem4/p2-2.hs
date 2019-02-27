expLn2 :: Int -> Int
expLn2 n = 2 ^ n

getFirst :: Int -> [Int]
getFirst n = map expLn2 oneTwoN 
                where oneTwoN = take n [1..]