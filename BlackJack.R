

cards <- c("Spades","Clubs","Hearts","Diamonds","Ace","King","Queen","Jack",2:10)

n2<-"yes"


while(n2=="yes"){


	#initial values
	carddrawn<-TRUE
	sum <- 0
	n<-""
	playedcards<-list()

	# while not over 21 or stayed

	while(n!="stay" && sum<21){

		n<-""
		
		# check to make sure card hasnt been drawn already


		while(carddrawn){


			carddrawn<-FALSE

			suit <- ceiling((runif(1)*4))	
			suit <- cards[suit]

			value <- ceiling((runif(1)*13))+4
			value <- cards[value]

			newcard<- c(value,suit)

			if(length(playedcards) != 0){
				for(i in 1:ncol(playedcards)){

					if((playedcards[1,i]== value) && (playedcards[2,i]== suit)){
						carddrawn<-TRUE
						print("found duplicate")
					}

				}
			}

			if(!carddrawn){
				playedcards<- cbind(playedcards[],newcard)
			}
			# draw 2 cards to start
			if(length(playedcards)<=2){
				carddrawn=TRUE	
			}

		}

		
		carddrawn=TRUE  #draw a new card next time
	
		

		sum=0
		ace=0 # number of aces

		cat("Your cards are\n")
		for(i in 1:ncol(playedcards)){

			cat(toString(playedcards[1,i])," of ", toString(playedcards[2,i]),"\n")


			if(playedcards[1,i] == "King" || playedcards[1,i] == "Queen" || playedcards[1,i] == "Jack"){ 
				sum = sum+10
			}else{
				if(playedcards[1,i] !="Ace"){
					sum = sum+as.integer(playedcards[1,i])
				}else {
					ace = ace + 1

				}

			}

		}

		if(ace>1){
			sum = sum + ace #if 2 or more automatically 1
		} else{
			if(ace !=0){
				if(sum+11 > 21){
					sum = sum+1
				}else{
					sum = sum +11
		
				}

			}

		} #end if


		while(n!= "hit" && n!="stay" && sum<21){
			n<-readline("Would you like to hit or stay?")

		} #end while


	}#end while 

	if(sum == 21){
		cat("BlackJack!\n")

	}else{

		if(sum >21){
			cat("Bust!\n")
		}else{

		cat("Your score is ",sum,"\n")
		}	


	}

	n2<-""
	while(n2!= "yes" && n2!="no"){
		n2<-readline("Would you like to play again? (yes or no)")

	}




}
