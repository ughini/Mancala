package gameelements;

import java.util.ArrayList;

/** The game board with 12 small pits and 2 big pits 
 * 
 * @author ughini
 *
 */

public class GameBoard {
	
	/** MUST be even number*/
	private static final int TOTAL_PITS = 14;
	
	private static final int HALF_PITS = 7;
	
	private static final int INIT_STONES = 6;
	
	private static final int PIT_SIZE = 100;
	
	ArrayList<Pit> pits;
	
	/** Initialize the game board with the proper number of pits
	 */
	public GameBoard() {
		
		this.pits = new ArrayList<Pit>();
				
		for( int i =0; i < TOTAL_PITS; i++) {
			Pit pit = new Pit(Player.PLAYER1, PitType.SMALL);
			pit.setStones(INIT_STONES);
			pit.setPosition(i+1, Position.BOTTOM);
			
			//change pits owner after the half
			if( i >= HALF_PITS ) {
				pit.setOwner(Player.PLAYER2);
				pit.setPosition(TOTAL_PITS - (i+1), Position.TOP);
			}
			
			//last player's pit is a big pit
			if( (i % HALF_PITS) == (HALF_PITS-1) ) {
				pit.setType(PitType.BIG);
				pit.setStones(0);
				pit.setYPosition(Position.TOP);
			}
			
			pits.add( pit );			
			
		}
	}
	
	public String print() {
		return "<img src='images/woodBoard.png' class='gameBoard'>";
	}
	
	public String printPits() {
		String rtn = "";
		for( int i=0; i < TOTAL_PITS; i++) {			
			rtn = rtn.concat("<img src= '" + pits.get(i).getImageFile() + "'"  
					      +  " class='"+ (pits.get(i).getType() == PitType.SMALL ? "pit" : "bigpit") + "'"
					      +  " onclick='pick(" + i +")' "
					      +  " style='left: " + ((pits.get(i).getXPosition() * PIT_SIZE) + PIT_SIZE) + "px; "
					     	 	  + " top: "  + ((pits.get(i).getYPosition() * PIT_SIZE) ) + "px;'> \n");
		}
		return rtn; 
	}

	
	
	

}
