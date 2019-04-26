add :: (Int, Int) -> [(Int, Int)] -> [(Int, Int)]
add e [] = [e]
add (value, priority) ((v,p):tail) 
										| priority > p = (value, priority) : (v, p) : tail
										| otherwise = (v, p) : (add (value,priority) tail)

getFirst :: [(Int, Int)] -> Maybe Int
getFirst [] = Nothing
getFirst ((v, p):tail) = Just (v)

getByPriority :: [(Int, Int)] -> Int -> Maybe Int
getByPriority [] _ = Nothing
getByPriority ((v,p):tail) pp 
									| p == pp = (Just v)
									| p > pp = getByPriority tail pp
									| otherwise = Nothing
							
select queue = do
    print ("enter action:")
    
    command <- getLine
    case command of
        'q':_ -> return ()
        '1':_ -> do
            value <- getLine
            priority <- getLine
            select (add (read value, read priority) queue)
        '2':_ -> do
            priority <- getLine
            print (getByPriority queue (read priority))
            select queue
        '3':_ -> do
            print (getFirst queue)
            select queue
        '4':_ -> do
            print (queue)
            select queue
        _ ->
            select queue

main = do
    select []