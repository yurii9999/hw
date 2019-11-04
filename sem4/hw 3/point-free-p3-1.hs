--func x l = map (\y -> y * x) l
-- eta reduction
--func x = map (\y -> y * x)
-- eta reduction
--func x = map (*x)
-- composition, eta reduction
func = map . (*)
