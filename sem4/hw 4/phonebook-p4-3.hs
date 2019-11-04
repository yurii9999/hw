import System.IO
import Control.Exception

data Person =
	Person {name :: String,
		number :: String}

instance Show Person where
	show person = "Name: " ++ name(person) ++ " Number: " ++ number(person)

encode :: Person -> String
encode p = name(p) ++ "#" ++ number(p)

decode :: String -> Person
decode str = Person na nu where
	simpleCorrect :: String -> String
	simpleCorrect [] = []
	simpleCorrect ('#':xs) = ' ':(simpleCorrect xs)
	simpleCorrect (x:xs) = x:(simpleCorrect xs)
	na:nu:xs = words $ simpleCorrect str
	 
main = do
	let phonebook = [Person "John" "9213901230312", Person "Larry" "930187372031"]
	doLoop phonebook

doLoop phonebook = do
	putStr("Enter command: ")
	command <- getLine
	case command of
		'0':_ -> return()
		'1':_ -> newPerson >>= (\p -> doLoop (p:phonebook))
		'2':_ -> findNumber phonebook >> doLoop phonebook
		'3':_ -> findName phonebook >> doLoop phonebook
		'4':_ -> save "fn" phonebook >> doLoop phonebook
		'5':_ -> load "fn" >>= (\pb -> doLoop pb)
		'6':_ -> putStrLn(show(phonebook)) >> doLoop phonebook
		_ -> doLoop phonebook

newPerson :: IO(Person)
newPerson = do
	putStr("Enter name: ")
	name <- getLine
	putStr("Enter number: ")
	number <- getLine
	return(Person name number)

findNumber :: [Person] -> IO()
findNumber phonebook = do
	putStr("Enter name: ")
	name1 <- getLine
	let lst = filter (\p -> (name(p) == name1)) phonebook
	if (length lst == 0) then putStr("No number") else putStr("Name: " ++ name1 ++ " Number: " ++ number(head lst))

findName :: [Person] -> IO()
findName phonebook = do
	putStr("Enter number: ")
	number1 <- getLine
	let lst = filter (\p -> number(p) == number1) phonebook
	if (length lst == 0) then putStr("No name") else putStr("Name: " ++ name(head lst) ++ " Number: " ++ number1)

save :: String -> [Person] -> IO()
save filename phonebook = do 
	let content = foldr (\s1 s2 -> s1 ++ " " ++ s2) "" (map (encode) phonebook)
	bracket (openFile filename WriteMode)
		hClose
		(\h -> hPutStrLn h content)


load :: String -> IO([Person])
load filename = 
	bracket (openFile filename ReadMode)
		hClose
		(\h -> do
			content <- hGetContents h
			let namesAndNumber = words content
			let pb = map decode namesAndNumber
			putStrLn("New phonebook: " ++ show(pb)) -- ?laziness? is reason of "hGetContents: illegal operation (delayed read on closed handle)" exception without it
			return (pb))
