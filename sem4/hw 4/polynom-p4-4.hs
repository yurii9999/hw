data Polynom = Polynom [Double]
instance Show Polynom where
        show (Polynom coeffs)
                | (coeffs == [])  = "0"
                | otherwise = tail $ foldl (\s1 s2 -> if (s2 == "") then (s1) else ( s1 ++ "+" ++ s2)) "" $ map (\(deg, coeff) -> printMonomial deg coeff) (zip [0..] coeffs)
                        where
                        printMonomial :: Int -> Double -> String
                        printMonomial _ 0 = ""
                        printMonomial 0 c = printCoeff c
                        printMonomial 1 c = printCoeff c ++ "x"
                        printMonomial d c = printCoeff c ++ "x^" ++ show(d)
                        printCoeff :: Double -> String
                        printCoeff c = if (c >= 0) then show(c) else ("(" ++ show(c) ++ ")")
        

sum1 :: Polynom -> Polynom -> Polynom
sum1 (Polynom coeffs1) (Polynom coeffs2) = Polynom (zipLongest (+) coeffs1 coeffs2)

multiplication :: Polynom -> Polynom -> Polynom
multiplication (Polynom coeffs1) (Polynom coeffs2) = let
        shift :: Int -> [Double] -> [Double]
        shift 0 lst = lst
        shift n lst = shift (n - 1) (0:lst)
        
        coeffsWithShifts = zip [0..] coeffs1
        sumItThen = map (\(s, c) -> shift s (map (*c) coeffs2)) coeffsWithShifts 
        resultCoeffs = foldr (\c1 c2 -> zipLongest (+) c1 c2) [] sumItThen
        in Polynom resultCoeffs
zipLongest :: (a -> a -> a) -> [a] -> [a] -> [a]
zipLongest _ [] ys = ys
zipLongest _ xs [] = xs
zipLongest f (x:xs) (y:ys) = (f x y) : zipLongest f xs ys

p1 = Polynom [1, 2, 4, 0, 4]
p2 = Polynom [1]
