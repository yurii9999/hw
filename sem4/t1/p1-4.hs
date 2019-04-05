supermap :: [a] -> (a -> [b]) -> [b]
supermap l f = (foldr (++) [].map f) l