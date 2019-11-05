getFibonacciNumber :: Int -> Int
getFibonacciNumber n 
	| n == 0 || n == 1 = 1 
	| n >= 0 = positiveFib n 1 1
	| n < 0 = negariveFib (-n) 1 1

positiveFib :: Int -> Int -> Int -> Int
positiveFib n cur prev 
	| n == 0 = cur
	| otherwise = positiveFib (n - 1) (prev + cur) (cur) 


negariveFib :: Int -> Int -> Int -> Int
negariveFib n cur prev
	| n == 0 = cur
	| otherwise = negariveFib (n - 1) (prev - cur) (cur)
