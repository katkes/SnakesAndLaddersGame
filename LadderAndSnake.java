/**
 * ======================================================================================= <br>
 * COMP 249 Assignment 1 Part I Due Date: 06/02/2023 @author Keshan Kathiripilay 40249407 <br>
 * =======================================================================================
 */

import java.util.Scanner;
public class LadderAndSnake {
	
	
	/**
	 * Simulates the action of a dice
	 * @return A random integer between 1 and 6 inclusively 
	 */
	public static int flipDice(){
		double hold = Math.random();
		
		if (hold < (double) (1.0/6)) {
			return 1;
		}
		
		else if ((hold > (double) (1.0/6)) && (hold < (double) (2.0/6))) {
			return 2;
		}
		
		else if ((hold >= (double) (2.0/6)) && (hold < (double) (3.0/6))) {
			return 3;
		}
		
		else if ((hold >= (double) (3.0/6)) && (hold < (double) (4.0/6))) {
			return 4;
		}
		
		else if ((hold >= (double) (4.0/6)) && (hold < (double) (5.0/6))) {
			return 5;
		}
		
		else if ((hold >= (double) (5.0/6)) && (hold < (double) (6/6))) {
			return 6;
		}
		
		return 0;
		
		
	}
/**
 * This method is the game engine 
 * 
 */
	public static void play(){
		
		Scanner key = new Scanner(System.in);
		int numplayer; // This is used as a holder for amount of players
		int count = 1;
		Player player1, player2;
		Tile[][] board = new Tile[10][];{     // The idea behind the board is that it's built 
			board[0] = new Tile[11];          // upon multiple tiles stored in a 2-D array. 
			board[1] = new Tile[10];          // The following lines until the next comments 
			board[2] = new Tile[10];          // demonstrate the process of initializing the board.
			board[3] = new Tile[10];
			board[4] = new Tile[10];
			board[5] = new Tile[10];
			board[6] = new Tile[10];
			board[7] = new Tile[10];
			board[8] = new Tile[10];
			board[9] = new Tile[10];
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = new Tile(); 
			}
		}
		board[0][10] = new Tile();		
		
		board[0][1].setLadder(true);
		board[0][1].setLadderEnd(38);
		board[0][4].setLadder(true);
		board[0][4].setLadderEnd(14);
		board[0][9].setLadder(true);
		board[0][9].setLadderEnd(31);
		board[2][0].setLadder(true);
		board[2][0].setLadderEnd(42);
		board[2][7].setLadder(true);
		board[2][7].setLadderEnd(84);
		board[3][5].setLadder(true);
		board[3][5].setLadderEnd(44);
		board[5][0].setLadder(true);
		board[5][0].setLadderEnd(67);
		board[7][0].setLadder(true);
		board[7][0].setLadderEnd(91);
		board[7][9].setLadder(true);
		board[7][9].setLadderEnd(100);
		
		board[1][5].setSnake(true);
		board[1][5].setSnakeTail(6);
		board[4][7].setSnake(true);
		board[4][7].setSnakeTail(30);
		board[6][1].setSnake(true);
		board[6][1].setSnakeTail(19);
		board[6][3].setSnake(true);
		board[6][3].setSnakeTail(60);
		board[9][2].setSnake(true);
		board[9][2].setSnakeTail(68);
		board[9][4].setSnake(true);
		board[9][4].setSnakeTail(24);
		board[9][6].setSnake(true);
		board[9][6].setSnakeTail(76);
		board[9][7].setSnake(true);
		board[9][7].setSnakeTail(78);
		/**
		 * 
		 * Ladders 
		 * 1  -> 38
		 * 4  -> 14
		 * 9  -> 31
		 * 21 -> 42
		 * 28 -> 84
		 * 36 -> 44
		 * 51 -> 67
		 * 71 -> 91
		 * 80 -> 100
		 * Snakes 
		 * 16 -> 6 
		 * 48 -> 30
		 * 62 -> 19
		 * 64 -> 60
		 * 93 -> 68
		 * 95 -> 24
		 * 97 -> 76
		 * 98 -> 78
		 * 
		 */
		
	
		int temp = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				(board[i][j]).setPos(temp);       // This sets the position of each tile
				temp++;
			}
		}
		
		Tile.setBoard(board); // A 2-D array of tiles is how the game is being represented 
							  // The board itself is made in the play method but being stored and manipulated in the Tile class
		
		
		
		// The following verifies the number of players 
		System.out.println("Welcome to snakes and ladders! Please enter how many players you would like to play with!");
		boolean a = key.hasNextInt(); // hasNextInt is used to troubleshoot when a non-integer is given
		String b = key.next();
		while (a == false) {
			System.out.println("Sorry! An invalid response was given!" + "\n" + "Please enter an integer value of how many players you would like to play with!");
			a = key.hasNextInt();
			b = key.next();
		}
		numplayer = Integer.parseInt(b);
		if (numplayer != 2) {
			if (numplayer < 2) {
				System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
				System.exit(0);
			}
			else {
				System.out.println("Initialization was attempted for " + numplayer + " member of players." + "\n" + "However, this is only expected for an extended version the game. Value will be set to 2" + "\n");
				numplayer = 2; 
			}
		}
		
		System.out.println("Player 1, please enter your name");
		String name1 = key.next();
		System.out.println("Player 2, please enter your name");
		String name2 = key.next();
		
		int hold1 = 0; 
		int hold2 = 0;
		int checkp1 = -1;
		int checkp2 = -1;

		System.out.println("Now deciding which player will start playing"); // the code to decide who's going first 
		while (hold1 == hold2) {
			hold1 = LadderAndSnake.flipDice();
			System.out.println(name1 + " got a dice value of " + hold1);
			hold2 = LadderAndSnake.flipDice();
			System.out.println(name2 + " got a dice value of " + hold2);
			if (hold1 == hold2) {
				System.out.println("");
				count++;
			}
		}
		
		if (hold1 > hold2) {
			System.out.println("Player " + name1 + " goes first!");
			player1 = new Player(name1);
			player2 = new Player(name2);}
		
		else {
			System.out.println("Player " + name2 + " goes first!");
			player1 = new Player(name2);
			player2 = new Player(name1);
		}
		System.out.println("It took " + count + " cycle(s) to determine the winner!");
		System.out.println("************************************************");

		do { // the cycle of the game 
				System.out.println("Rolling dice for " + player1.getName() + "!");
				System.out.println("Enter any key to roll!");
				String n = key.next(); // Holds the input to roll
				hold1 = LadderAndSnake.flipDice();
				System.out.println(player1.getName() + " got " + hold1);
				checkp1 = player1.getPos() + hold1;
				if (checkp1 == 100)
					break;
				player1.move(checkp1);
				Tile.displayBoard(player1, player2);
				System.out.println("Rolling dice for " + player2.getName() + "!");
				System.out.println("Enter any key to roll!");
				n = key.next();        // Holds the input to roll
				hold2 = LadderAndSnake.flipDice(); 
				System.out.println(player2.getName() + " got " + hold2);
				checkp2 = player2.getPos() + hold2;
				if (checkp2 == 100)
					break;
				player2.move(checkp2);
				Tile.displayBoard(player1, player2);
			
		} while ((checkp1 != 100) || (checkp2 != 100));
		
		if (checkp1 == 100) {
			System.out.print("Congratulations " + player1.getName() + ", you've won the game!" + "\n" + "Thank you for playing Keshan's Snakes and Ladders!");
		}
		
		else if (checkp2 == 100) {
			System.out.print("Congratulations " + player2.getName() + ", you've won the game!" + "\n" + "Thank you for playing Keshan's Snakes and Ladders!");
		}

		System.exit(0);
		key.close();
	
	}
	
	
}
