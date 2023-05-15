/**
 * ======================================================================================= <br>
 * COMP 249 Assignment 1 Part I Due Date: 06/02/2023 @author Keshan Kathiripilay 40249407 <br>
 * =======================================================================================
 */


public class Player {

	private int pos = 0; 
	private String name; 
	
	/** 
	 * A getter method of the tile position of a player
	 * @return Integer number that represents the player's current tile position
	 */
	
	public int getPos() {
		return pos;
	}

	/**
	 * The mechanic of moving to a new tile. The previous tile would have their occupancy parameter set to false as it's not 
	 * occupied anymore and the current tile will have the opposite reaction. 
	 * @param pos Integer number that represents the new tile position of the player 
	 */
	public void setPos(int pos) {
		int[] hold = Tile.getTileNum(this.pos);
		Tile.getBoard()[hold[0]][hold[1]].setOccupied(false);
		this.pos = pos;
		int[] hold2 = Tile.getTileNum(pos);
		Tile.getBoard()[hold2[0]][hold2[1]].setOccupied(true);
		System.out.println(this);
	}
	
	/**
	 * A getter method to get the name of a Player object
	 * @return A string that is the player's name
	 */

	public String getName() {
		return name;
	}

	/** 
	 * A setter method for the name attribute of the Player object
	 * @param name The player's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * The mechanic of someone landing one a snake's head and going down to the snake's tail
	 * @param snakeTail The tile number of the snake's tail where the player will end up being at 
	 */

	public void getSnaked(int snakeTail) {
		this.pos = snakeTail;
		System.out.println("Sorry! You've got snaked!" + "\n" + "Returning player to " + snakeTail +".");
	}
	
	/**
	 * The mechanic of a player getting pushed off a tile because someone was already on it
	 */
	
	public void getPushed() {
		this.pos = 0;
		System.out.println("Sorry! The tile is already occupied! Moving player to the start!");
	}
		
	/**
	 * The mechanic of a player climbing a ladder 
	 * @param ladderEnd The tile number of the end of the ladder, which would be where the player would end up
	 */
	
	public void climbLadder(int ladderEnd) {
		this.pos = ladderEnd;
		System.out.println("Lucky you, you've reached a ladder!");
		System.out.println("Now taking " + name + " to position " + ladderEnd);		
	}
		
	
	/**
	 * A constructor for a player with the name being given
	 * @param name The player's name
	 */
	public Player(String name) {
		this.setName(name);
	}
	
	/**
	 * The movement mechanic from the perpsective of the player 
	 * @param tileNum The tile that the player would be moving to
	 */
	
	public void move(int tileNum) {
		if (tileNum > 100) {
			tileNum -= 2 * (tileNum - 100);
			int[] hold = Tile.getTileNum(tileNum);
			Tile.getBoard()[hold[0]][hold[1]].playTile(this);
		}
		int[] hold = Tile.getTileNum(tileNum);
		Tile.getBoard()[hold[0]][hold[1]].playTile(this);
	}
	
	/**
	 * Displays the name and position of a player 
	 */
	public String toString() {
		return name + " is at position " + pos;
	}
}
