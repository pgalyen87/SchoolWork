
#include <stdlib.h>
#include <stdio.h>
#include <mpi.h>
#define WIDTH 80
#define HEIGHT 40

int findNeighbors(char grid[HEIGHT][WIDTH], int x, int y);
void blankGrid(char grid[HEIGHT][WIDTH]);
int shouldExist(char grid[HEIGHT][WIDTH], int x, int y, int numNeighbors);

// determines whether a cell should exist in the next generation
int shouldExist(char grid[HEIGHT][WIDTH], int x, int y, int numNeighbors){

	int exists = 0;

				
	if (grid[x][y] == 'O'){
		exists = 1; 
						
	}


	if(((numNeighbors == 2) || (numNeighbors == 3)) && exists ==1){
		//stay alive
		return 1;

	}else {

						
		if (numNeighbors == 3 && exists == 0){

			// spawn new
			return 1;




		} else {

			//dies or stays dead		
			return 0;			

		
		}

	}


}



// finds the number of neighbors from a grid and an x y point
int findNeighbors(char grid[HEIGHT][WIDTH],int x,int  y){

	int yN;
	int xN;

	int numNeighbors = 0;

	for(yN = -1; yN<2; yN++){
		for(xN = -1; xN<2; xN++){
							
			// if neighbors in bounds
			if ((yN+y >=0) && (yN+y < WIDTH) && (xN+x >=0) && (xN+x < HEIGHT) && !(xN==0 && yN==0)){


				// increment number of neighbors
									
				if(grid[x+xN][y+yN] == 'O'){
									
					numNeighbors += 1;
								

				}
									

			}	
									

		}
	}
		
	return numNeighbors;
}



// initializes grid to all blanks
void blankGrid(char grid[HEIGHT][WIDTH]){

	int x;
	int y;

		for(x= 0; x< HEIGHT; x++){
			for(y=0; y<WIDTH;y++){
					
			grid[x][y] = ' ';	
			
		 	}

		}

}



int main(int argc,char *argv[]){

	//number of generations
	int N;
	int rank, size;

	
	FILE *input;
	input = fopen(argv[1],"r");
	if(!input){
		printf("Failed to open file");
		exit(0);
	}

	MPI_Init(&argc, &argv);

	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	MPI_Status status;

	N = atoi(argv[2]);

	
	char gridEven[HEIGHT][WIDTH];
	char gridOdd[HEIGHT][WIDTH];
	char temp;

	int numNeighbors;
 	int x;
	int y;
	int i;
	int j;
	int start;
	int end;	


	// read in grid starting configuration generation 0
	for(x=0; x<HEIGHT; x++){
		
		for(y=0; y<WIDTH+1;y++){

		temp  = (char)fgetc(input);
		if(temp != '\n'){
			gridEven[x][y] = temp;
		}

	
		}
	}

	
	
	start = (int) (HEIGHT/size)* rank;
	end = (int)start + (HEIGHT/size);
	
	// calculate next generations
	for(i =1; i< N; i++){
			
	
	if((i%2)==0){

		blankGrid(gridEven);


		} else{

		blankGrid(gridOdd);

		}
	


		

		for(x= start; x < end; x++){
			

			for(y=0; y<WIDTH;y++){
						
					
				//find neighbors of previous  generation
				if (i% 2 == 0) {
 				
					numNeighbors = findNeighbors(gridOdd, x, y);	
					if(shouldExist(gridOdd, x, y, numNeighbors) == 1){
						gridEven[x][y] = 'O';

					}

				}else {



					numNeighbors = findNeighbors(gridEven, x ,y);
					
					if(shouldExist(gridEven, x, y, numNeighbors) == 1){
						gridOdd[x][y] = 'O';

					}


				}


			} // end y

		} // end x
		
		MPI_Barrier(MPI_COMM_WORLD);


		
		int count;
		
	
		if(i%2 == 0){

			//even generation............
			
		//even ranks send
			if (rank%2 ==0){

				//send bottom row
				if( rank != 0)
					MPI_Send(&gridEven[start], WIDTH, MPI_CHAR, rank-1,0, MPI_COMM_WORLD);
				

				//send top row
				if( rank+1 != size)
					MPI_Send(&gridEven[end-1], WIDTH, MPI_CHAR, rank+1, 0,MPI_COMM_WORLD);
				
			

			} else {

				// odd ranks receive
				//receive bottom row
				if(rank+1 != size)
					MPI_Recv(&gridEven[end], WIDTH, MPI_CHAR, rank+1,0, MPI_COMM_WORLD, &status);


				//receive top row
				if (rank != 0)
					MPI_Recv(&gridEven[start-1], WIDTH, MPI_CHAR, rank-1, 0,MPI_COMM_WORLD, &status);

			}


			MPI_Barrier(MPI_COMM_WORLD);

			//even ranks receive
			if (rank%2 ==0){
				
				//receive bottom row
				if(rank+1 != size)	
					MPI_Recv(&gridEven[end], WIDTH, MPI_CHAR, rank+1,0, MPI_COMM_WORLD, &status);
				//receive top row
				
				if (rank != 0)
					MPI_Recv(&gridEven[start-1], WIDTH, MPI_CHAR, rank-1,0, MPI_COMM_WORLD, &status);

			} else {

				// odd ranks send
				//send bottom row
				if( rank != 0)
					MPI_Send(&gridEven[start], WIDTH, MPI_CHAR, rank-1,0, MPI_COMM_WORLD);
				
				//send top row
				if(rank+1 != size)
					MPI_Send(&gridEven[end-1], WIDTH, MPI_CHAR, rank+1, 0,MPI_COMM_WORLD);

			}





		} else { 

			// odd generation ...........

			//even ranks send


			if (rank% 2 == 0){
				
				//send bottom row
				if( rank != 0)
					MPI_Send(&gridOdd[start], WIDTH, MPI_CHAR, rank-1, 0,MPI_COMM_WORLD);
				
				//send top row
				if( rank+1 != size)
					MPI_Send(&gridOdd[end-1], WIDTH, MPI_CHAR, rank+1, 0,MPI_COMM_WORLD);
				

			} else {
				// odd ranks receive
				// receive bottom row
				if (rank+1 != size){
					MPI_Recv(&gridOdd[end], WIDTH, MPI_CHAR, rank + 1, 0,MPI_COMM_WORLD, &status);
	
				int error;
				error = MPI_Get_count(&status, MPI_CHAR, &count);
				}
				//receive top row
				if (rank !=0){
					MPI_Recv(&gridOdd[start -1], WIDTH, MPI_CHAR, rank-1, 0,MPI_COMM_WORLD, &status);
						
				}
			}


			MPI_Barrier(MPI_COMM_WORLD);

			//even ranks receive
			if (rank% 2 == 0){

				//receive bottom row
				if (rank+1 != size){
					MPI_Recv(&gridOdd[end], WIDTH, MPI_CHAR, rank+ 1, 0,MPI_COMM_WORLD, &status);
				}

				//receive top rowa
				if (rank !=0)
					MPI_Recv(&gridOdd[start -1], WIDTH, MPI_CHAR, rank-1, 0,MPI_COMM_WORLD, &status);
				


			} else {
				// odd ranks

				//send bottom row
				if (rank !=0)
					MPI_Send(&gridOdd[start], WIDTH, MPI_CHAR, rank-1, 0,MPI_COMM_WORLD);

				//send top row
				if(rank+1 != size)
					MPI_Send(&gridOdd[end-1], WIDTH, MPI_CHAR, rank+1, 0,MPI_COMM_WORLD);
				



			}
	
		

		}
	
	//	MPI_Barrier(MPI_COMM_WORLD);



	} // end N

	
	MPI_Barrier(MPI_COMM_WORLD);


	//if (rank == 0)
	//	blankGrid(gridEven);

	// get final grid from all processes
	if((N-1) %2 == 0){
			
			
			if ( rank != 0 ){
				for(x = start; x < end; x++){
					MPI_Send(&gridEven[x], WIDTH, MPI_CHAR, 0, 0, MPI_COMM_WORLD);
				}
			}
				if (rank == 0){
					
					for (i = 1 ; i < size; i++){
					

						for(x = start; x < end; x++){
						
						int start2;	
						start2 = (int)(HEIGHT/size)* i;
						MPI_Recv(&gridEven[start2+x], WIDTH, MPI_CHAR, i, 0 ,MPI_COMM_WORLD, &status);

						
						}
					}
				}
	} else {
	
			if ( rank != 0 ){
				for(x = start; x < end; x++){
					MPI_Send(&gridOdd[x], WIDTH, MPI_CHAR, 0, 0, MPI_COMM_WORLD);
				}
			}
				if (rank == 0){
					
					for (i = 1 ; i < size; i++){
					

						for(x = start; x < end; x++){
						
						int start2;	
						start2 = (int)(HEIGHT/size)* i;
						MPI_Recv(&gridOdd[start2+x], WIDTH, MPI_CHAR, i, 0 ,MPI_COMM_WORLD, &status);

						
						}
					}
				}
	

	}



	// print final generation
	if (rank == 0){

		if (((N-1)%2) == 0){

			for(x=0; x<HEIGHT; x++){
				for(y=0; y<WIDTH;y++){


					printf("%c", gridEven[x][y]);

				}
				printf("\n");

			}
		} else{

			for(x=0; x<HEIGHT; x++){
				for(y=0; y<WIDTH;y++){


					printf("%c", gridOdd[x][y]);

				}
				printf("\n");

			}






		}

	}


	MPI_Finalize();

}






