-- assume expression contains Consts, Variable, +, -, *, / and is correct

import Data.List

data Monomial = Monomial {coeff :: Int, deg :: Int}
	deriving (Eq)
instance Ord Monomial where
	compare (Monomial _ dg1) (Monomial _ dg2) = compare dg1 dg2
instance Show Monomial where
	show (Monomial cff dg) | cff == 1 = if (dg == 0) then ("1") else(pow dg)
		| cff > 0 = show(cff) ++ (pow dg)
		| cff < 0 = "(" ++ show(cff) ++ ")" ++ (pow dg)
		| cff == 0 = ""
		where pow dg | dg == 0 = ""
			| dg > 0 = init $ foldr (\a b -> a ++ "*" ++ b) "" $ map (\c -> "x") [1..dg]
			| dg < 0 = "1/" ++ (init $ foldr (\a b -> a ++ "/" ++ b) "" $ map (\c -> "x") [1..(-dg)])

derivativeMonomial :: Monomial -> Monomial
derivativeMonomial (Monomial cff dg) = if (dg == 0)
		then (Monomial 0 0)
		else (Monomial (cff * dg) (dg - 1))

splitTokens :: String -> [String]
splitTokens "" = []
splitTokens (x:xs) | x `elem` ['+', '-', '*', '/', 'x'] = (x:"") : splitTokens xs
	| x == ' ' = splitTokens xs
	| otherwise = splitTokens' (x:"") xs

splitTokens' :: String -> String -> [String]
splitTokens' "" "" = []
splitTokens' acc "" = [acc]
splitTokens' acc (' ':xs) = splitTokens' acc xs
splitTokens' acc (x:xs) | x `elem` ['+', '-', '*', '/', 'x'] = acc : (splitTokens (x:xs))
	| x == ' ' = splitTokens xs
	| otherwise = splitTokens' (acc ++ (x:"")) xs

parseExpression :: [String] -> [Monomial]
parseExpression (t:ts) | t == "-" = parseExpression' ts (Monomial (-1) 0)
	| t == "+" = parseExpression ts
	| t == "x" = parseExpression' ts (Monomial 1 1)
	| otherwise = parseExpression' (t:ts) (Monomial 1 0)

parseExpression' :: [String] -> Monomial -> [Monomial]
parseExpression' [] monom = [monom]
parseExpression' ("+":ts) monom = monom:parseExpression ("+":ts)
parseExpression' ("-":ts) monom = monom:parseExpression ("-":ts)

parseExpression' ("x":[]) (Monomial cff dg) = [Monomial cff (dg + 1)]
parseExpression' (t:[]) (Monomial cff dg) = [Monomial (cff * (read t :: Int)) dg] -- assume expression is correct

parseExpression' ("*":"x":ts) (Monomial cff dg) = parseExpression' ts (Monomial cff (dg + 1))
parseExpression' ("/":"x":ts) (Monomial cff dg) = parseExpression' ts (Monomial cff (dg - 1))
parseExpression' ("*":c:ts) (Monomial cff dg) = parseExpression' ts (Monomial (cff * (read c :: Int)) dg)
parseExpression' ("/":c:ts) (Monomial cff dg) = parseExpression' ts (Monomial (cff `div` (read c :: Int)) dg)

parseExpression' ("x":ts) (Monomial cff dg) = parseExpression' ts (Monomial cff (dg + 1))
parseExpression' (t:ts) (Monomial cff dg) = parseExpression' ts (Monomial (cff * (read t :: Int)) dg)
derivative :: [Monomial] -> [Monomial]
derivative = map derivativeMonomial

optimize :: [Monomial] -> [Monomial]
optimize expr = filter (\m -> (coeff m) /= 0) $
		map (\g -> foldr(\x y -> Monomial (coeff(x) + coeff(y)) (deg x)) (Monomial 0 0) g) $ 
		groupBy (\a b -> (deg a) == (deg b)) $
		sort expr

showExpression :: [Monomial] -> String
showExpression [] = "0"
showExpression expr = init $ showExpression' expr

showExpression' [] = ""
showExpression' (m:ms) = show(m) ++ "+" ++ (showExpression' ms)

derivation :: String -> String
derivation = showExpression . optimize . derivative . parseExpression . splitTokens

example = "x*x*x + 3*x + 1 + 5*x+ x*x*x*x*x + 5*x*x*x"
