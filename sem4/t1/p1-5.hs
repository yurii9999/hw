isGood :: (a -> Bool) -> [a] -> Bool
isGood p (head:tail) = if p head
                                then isGood p tail
								else False
isGood _ [] = True