import System.Random

customize :: [Int] -> IO([Int])
customize [] = return []
customize (h:t) = do 
		randomValue <- getStdRandom (randomR(1, 10))
		newList <- customize t
		return (randomValue:newList)