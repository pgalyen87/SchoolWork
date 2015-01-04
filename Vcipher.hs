import Data.Char

main = do
	putStrLn("Enter text:")
	text <- getLine

	putStrLn("Enter Key:")
	key <- getLine
	
	
	
	let converted = convert text [] key 0 0
	
	putStr(converted)


convert "" newword akey wordcount keycount = newword



convert word newword akey wordcount keycount = do 
	

let wordlength = length word

let ascw = if not (wordcount == wordlength)
	then  ord (word !! wordcount)
	else 0
 
let keylength = length akey 
let keycount2 = if keycount < keylength 
	then keycount 
	else  0



let asck = if not (wordcount == wordlength)
	then ord (akey !! keycount2)
	else 0	

let sum = ascw + asck

let add = if (mod (sum-122) 26) == 0
	then 26
	else (mod (sum-122) 26)


let sum2 = if ascw >= 97 
	then (96 + add)
	else ascw	


let letter = if (sum2>0)
	then [chr sum2]
	else []

let newerword = newword ++ letter


if (wordlength == wordcount)
	then convert "" newword akey wordcount keycount
	else convert word newerword akey (wordcount+1) (keycount2 +1) 

	




