isCorrectString :: String -> Bool
isCorrectString l = isCorrectString' (filter (\a -> a `elem` ['(', ')', '[', ']', '{', '}']) l) ""

isCorrectString' :: String -> String -> Bool
isCorrectString' "" "" = True
isCorrectString' "" _ = False
isCorrectString' (x:xs)  "" 
	| x `elem` ['(', '[', '{'] = isCorrectString' xs [x]
	| otherwise = False
isCorrectString' (x:xs) (y:ys) 
	| x `elem` ['(', '[', '{'] = isCorrectString' xs (x:y:ys) 
	| x `elem` [')', ']', '}'] = isCorresponce x y && isCorrectString' xs ys
	| otherwise = False

isCorresponce :: Char -> Char -> Bool
isCorresponce y x
	| x == '(' && y == ')' = True
	| x == '[' && y == ']' = True
	| x == '{' && y == '}' = True
	| otherwise =  False
