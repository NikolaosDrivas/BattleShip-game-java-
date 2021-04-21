package project2;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;


public class battle {

	public static void main(String[] args) {
	int BoardLength = 9;
	char blank = 'B';
	char ship = 'S';                
	char miss = 'M';
	char hit = 'H';
	int boatSquare=9;
	int PatrolHits=0;
	int sunks=0;
	char restart;
	Scanner obj =new Scanner(System.in);
	char[][] Board =createBoard(BoardLength, blank, ship);
	int i=0;
	do {
		int tries=0;
		
	while(tries<10) {		//user has 10 tries
		int[] guessCoordinates =getUserCoordinates(BoardLength); //user coordinates
		char locationUpdate = evaluteTarget(guessCoordinates,Board,ship,blank,hit,miss,boatSquare);
		boatSquare=evaluteTarget1(guessCoordinates,Board,ship,blank,hit,miss,boatSquare); //how many bootsquares have been left
		Board = updateBoard(Board,guessCoordinates,locationUpdate); //array that updates miss and hit, when user gives coordinates
		printBoard(Board,blank,ship);    
		
		 tries++;
		    System.out.println("exeis kanei prospatheies:"+ tries);
		}
	
	  if(boatSquare==0) {
			System.out.println("You won");
		}else {
		 System.out.println("Game Over");
		}
	  
	  System.out.println("ta ploia eixan topo8eth8ei ston pinaka ws e3hs");  //
	    for( i=0; i<9; i++) {
			System.out.print(i +1 + " ");
		    for(int j=0; j<9; j++) {
		    	System.out.print(Board[i][j]);
		    	}
		    System.out.println();
	    }
	    
	    
	    System.out.println("thelete na 3ana pai3ete");
		System.out.println("ean nai pathste y alliws opoiodhpote allo gramma");
		restart= obj.next().charAt(0); //if user wants to play again
	}while(restart== 'y');
	System.out.println("euxaristoume pou pai3ate");
	return;
	  }
	
	
    private static char[][] createBoard(int BoardLength,char blank,char ship) {
		char Board[][] =new char[BoardLength][BoardLength];
		for(char[] row : Board) {
			Arrays.fill(row, blank);
		} 
		Random random = new Random();
		int placedCruiserShips=0;
		int VerticalShips=0;
		int HorizontalShips=0;
		int placedPatrolShips=0;
		do {
			 int x = random.nextInt(BoardLength);
			 int y = random.nextInt(BoardLength);
			 boolean vertical = random.nextBoolean();
		
		      char possiblePlacement =Board[x][y];
	    	//puts boats horizontal or vertical (cruisers)
					if((possiblePlacement ==blank) &&(x>0) &&(y>0) &&(y<8)&&(x<8) &&(Board[x][y-1]==blank) &&(Board[x][y+1]==blank)
							&&(Board[x-1][y]==blank)&&(Board[x+1][y]==blank)) {
					if(vertical==true) {
						Board[x][y]= ship;
						Board[x][y+1]= ship;
						VerticalShips++;
					} else if((possiblePlacement ==blank) &&(x>0) &&(y>0) &&(x<8) &&(y<8) &&(Board[x][y-1]==blank) &&(Board[x][y+1]==blank)
							&&(Board[x-1][y]==blank)&&(Board[x+1][y]==blank)) {
						Board[x][y]= ship;
						Board[x+1][y]= ship;
						HorizontalShips++;
					}
					placedCruiserShips=	VerticalShips + HorizontalShips;
					}
				
	}while(placedCruiserShips<3);
		
		do {
			 int x = random.nextInt(BoardLength);
			 int y = random.nextInt(BoardLength);
		
		      char possiblePlacement =Board[x][y];
	    	//patrol boats that have blank squares to all directions
					if((possiblePlacement ==blank) &&(x>0) &&(y>0) &&(y<8) &&(x<8) &&(Board[x][y-1]==blank) &&(Board[x][y+1]==blank)
							&&(Board[x-1][y]==blank)&&(Board[x+1][y]==blank)) {
					
						Board[x][y]= ship;
						placedPatrolShips++;
					}
				}while(placedPatrolShips<3);

		System.out.println("ta cruiser karavia pou exoun topo8eth8ei einai:"+placedCruiserShips);
		System.out.println("ta patrol karavia pou exoun topo8eth8ei einai:"+placedPatrolShips);
		return Board;
	}
		
	
      private static void printBoard(char[][] Board, char blank, char ship) {
    	  //print ton pinaka
    	  System.out.print("  ");
    	  for(int row=0; row<9; row++) {
    		  System.out.print(row +1 + " ");
    	  }
    	  System.out.println();
    	  for(int i=0; i<9; i++) {
    		  System.out.print(i +1 + " ");
    	    for(int j=0; j<9; j++) {
    	    	char position =Board[i][j];
    	    	if(position==ship) {
    	    		System.out.print(blank + " ");
    	    	} else {
    	    		System.out.print(position + " ");
    	    	}	    	
    	    }
    	    System.out.println();
    	  }
    	  System.out.println();
		}
 
      
      private static int[] getUserCoordinates(int BoardLength) {
    	  int i,j;
    	  //user gives coordinates
    	  System.out.println("dwse tis suntetagmenes gia na vreis pou einai to karavi");
    	  do {
    		 
    		  System.out.print("Row: ");
    		  i =new Scanner(System.in).nextInt();
    		
    		  
    		  } while(i<1 || i >BoardLength );
    	  
    	  do {
    		  System.out.print("Column: ");
    		  j =new Scanner(System.in).nextInt();
    		  } while((j<1 || j> BoardLength ));
    	 
  		   return new int[] {i -1, j-1};
    	  
  	}
      
      
      private static char evaluteTarget(int[] guessCoordinates, char[][] Board, char ship, char blank, char hit,char miss,int boatSquare) {
  		String mhnuma;
  		int i=guessCoordinates[0];
  		int j=guessCoordinates[1];
  	    char target =Board[i][j];
  	    //evalute if user coordinates are hit, miss or already hit
  	    if(target==ship) {
  	    mhnuma = "Hit";
  	    target = hit;
  	    boatSquare--;
  	    }else if(target ==blank) {
  	    	mhnuma = "Miss";
  	    	target =miss;
  	    } else {
  	    	mhnuma ="Already hit";
  	    }
  	    System.out.println(mhnuma);
  		return target;
  	}
      
      private static int evaluteTarget1(int[] guessCoordinates, char[][] Board, char ship, char blank, char hit,char miss,int boatSquare) {
    		
    		int i=guessCoordinates[0];
    		int j=guessCoordinates[1];
    	    char target =Board[i][j];
    	    //evalute if you win or lose
    	    if(target==ship) {
    	    target = hit;
    	    boatSquare--;
    	    }else if(target ==blank) {
    	    	
    	    	target =miss;
    	    } 
    	   
    		return boatSquare;
    	}
     
      
      private static char[][] updateBoard(char[][] Board,int[] guessCoordinates,char locationUpdate) {
    	  //update board array with hit or miss
    	  int i=guessCoordinates[0];
    	  int j=guessCoordinates[1];
    	  Board[i][j] = locationUpdate;
  		return Board;
  	}
}
  