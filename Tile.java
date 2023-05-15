/**
 * ======================================================================================= <br>
 * COMP 249 Assignment 1 Part I Due Date: 06/02/2023 @author Keshan Kathiripilay 40249407 <br>
 * =======================================================================================
 */


public class Tile {
	
	private boolean snake = false;  
	private boolean ladder = false; 
	private boolean occupied = false; 
	private int snakeTail;
	private int ladderEnd;
	private int pos; 
	private static Tile[][] board;
	/**
	 * A setter method of the 2-D array of the static board variable
	 * @param gameBoard The board itself
	 */
	public static void setBoard(Tile[][] gameBoard) {
		board = gameBoard;
	}
	
	/**
	 * A getter method of the 2-D array of the static board variable
	 * @return The 2-D array of the static board variable
	 */
	public static Tile[][] getBoard() {
		return board;
	}
	
	/**
	 * Displays the board of the game between two players
	 * 
	 * @param p1 One of the two players
	 * @param p2 The second of two players
	 */
	
	public static void displayBoard(Player p1, Player p2) {
		
		int hold1 = p1.getPos();
		int hold2 = p2.getPos();
		System.out.println("------------------------------------------------------------------");
		System.out.print("Start : ");
		if (hold1 == 0)
			System.out.print(p1.getName());
		if (hold2 == 0)
			System.out.print(p2.getName());
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				if (i == 0) {
					if (j == 0)
						continue;
					for (int k = 1; k < board[i].length; k++) {
						if ((i * 10 + k == hold1) && (hold1 != 0)){
							System.out.print(p1.getName() + " ");
						}
						if ((i * 10 + k == hold2) && (hold2 != 0)) {
							System.out.print(p2.getName() + " ");
						}
						System.out.print(board[i][k].getPos() + "  | ");
					}
					break;
				}
				if (i * 10 + (j + 1) == hold1){
					System.out.print(p1.getName() + " ");
				}
				if (i * 10 + (j + 1) == hold2) {
					System.out.print(p2.getName() + " ");
				}
				System.out.print(board[i][j].getPos() + " | ");
			}
			System.out.print("\n");
			System.out.println("------------------------------------------------------------------");
		}
	}
	
	/**
	 * Method used to go from a tile number to the index of the corresponding tile in the board
	 * 
	 * @param num The tile number
	 * @return A 2-D array of the index of the tile
	 */
	
	public static int[] getTileNum(int num) {
		
		if (num < 11) {
			int[] ret = {0,num};
			return ret;
		}
		
		else if (num % 10 == 0) {
			num -= 1;
			int x = num / 10;
			int y = num - (x * 10);
			int[] ret = {x,y};
			return ret;
		}
		
		int x = num / 10;
		int y = num - (x * 10) - 1;
		int[] ret = {x,y};
		return ret;
	}
	
	/**
	 * Executes the decision making of the tile that's being landed on 
	 * @param player The player that lands on the tile
	 */
	
	public void playTile(Player player) {
		if (this.isOccupied(player))
			return;
		else if (this.isSnake(player))
			return;
		else if (this.isLadder(player))
			return;
		else {
			player.setPos(pos);
		}
	}
	
	/**
	 * Checks whether the tile is a snake or not
	 * @param player The player that lands on the tile
	 * @return The boolean value of whether the tile is a snake or not 
	 */
	public boolean isSnake(Player player) {
		
		if (snake) {
			int[] hold = Tile.getTileNum(player.getPos());
			board[hold[0]][hold[1]].setOccupied(false);
			player.getSnaked(snakeTail);
			
		}
		return snake;
	}
	
	/**
	 * A setter method to set whether it is a snake or not
	 * 
	 * @param snake Boolean value of whether it is a snake or not
	 */
	public void setSnake(boolean snake) {
		this.snake = snake;
	}

	/**
	 * A setter method to set whether it is a ladder or not
	 * 
	 * @param ladder Boolean value of whether it is a ladder or not
	 */
	public void setLadder(boolean ladder) {
		this.ladder = ladder;
	}
	
	/**
	 * Checks whether the tile is occupied or not
	 * @param player The player that lands on the tile
	 * @return The boolean value of whether the tile is occupied or not 
	 */
	public boolean isOccupied(Player player) {
		if (occupied) {
			int[] hold = Tile.getTileNum(player.getPos());
			board[hold[0]][hold[1]].setOccupied(false);
			player.getPushed();			
		}
		return occupied;
	}
	
	/**
	 * A setter method to set whether it is occupied or not
	 * @param occupied Boolean value of whether it is occupied or not
	 */
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	/**
	 * A getter method to get the tile number of the end of the snake's tail
	 * @return An integer value that is the tile number of the end of the snake's tail
	 */
	public int getSnakeTail() {
		return snakeTail;
	}
	
	/**
	 * A setter method to set the snake's tail
	 * @param snakeTail Integer value of the tile number of the snake's tail
	 */

	public void setSnakeTail(int snakeTail) {
		this.snakeTail = snakeTail;
	}
	/**
	 * A getter method that gets the tile position of the end of the ladder
	 * @return An integer value of the tile position of the end of the ladder
	 */
	public int getLadderEnd() {
		return ladderEnd;
	}
	
	/**
	 * A setter method to set the ladder's end
	 * @param ladderEnd Integer value of the tile number of the ladder's end
	 */

	public void setLadderEnd(int ladderEnd) {
		this.ladderEnd = ladderEnd;
	}

	/**
	 * A getter method to get the tile's position 
	 * @return Integer value of the tile's position
	 */
	public int getPos() {
		return pos;
	}
	
	/**
	 * A setter method to set the position of the tile on the board
	 * @param pos Integer value of the tile number 
	 */

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	/**
	 * Checks whether the tile is a ladder or not
	 * @param player The player that lands on the tile
	 * @return The boolean value of whether the tile is a ladder or not 
	 */
	public boolean isLadder(Player player) { 
		if (ladder) {
			int[] hold = Tile.getTileNum(player.getPos());
			board[hold[0]][hold[1]].setOccupied(false);
			player.climbLadder(ladderEnd);
		}
		return ladder;	
	}
	
}
