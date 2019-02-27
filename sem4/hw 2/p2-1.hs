reverse' :: [a] -> [a]
reverse' list = reverseS list []

reverseS :: [a] -> [a] -> [a]
reverseS [] temp = temp
reverseS (head:tail) temp = reverseS tail (head:temp)