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
	
	public Pit getPit( int number) {
		if ( number >= 0 && number < TOTAL_PITS ) {
			return pits.get(number);
		}
		return null;
	}
	
	public Pit getNextPit( Pit current ) {
		for( int i=0; i < TOTAL_PITS; i++ ) {
			if (pits.get(i) == current) {				
				if( (i+1) < TOTAL_PITS ) {
					return pits.get(i+1);
				}
				return pits.get(0);
			}
		}
		return null;
	}

	//Search opposite pit of the current pit
	public Pit getOppositePit( Pit current ) {
		for( int i=0; i < TOTAL_PITS; i++ ) {
			if (pits.get(i) == current) {				
				return pits.get((TOTAL_PITS-2)-i);
			}
		}
		return null;
	}

	//Search for the big pit of a player	
	public Pit getPlayerBigPit( Player player ) {
		for( int i=0; i < TOTAL_PITS; i++ ) {
			if (pits.get(i).getType() == PitType.BIG && pits.get(i).getOwner() == player) {				
				return pits.get(i);
			}
		}
		return null;
	}
	
	//Checks if one side small pits are all empty
	public boolean emptySide() {
		boolean empty = true;
		for( int i=0; i < TOTAL_PITS; i++ ) {
			
			if ( pits.get(i).getType() == PitType.BIG) {
				// reached to the bigpit. Checks if allmpits were empty
				if (empty) {
					return true;
				}
				else {
					//resets flag for next player side
					empty = true;
					continue;
				}
			}
			
			if( pits.get(i).countStones() > 0 ) {
				empty = false;
			}
		}
		return false;
	}
	
	
	// Move all remaining stones to the respective big pit 
	public void cleanNotEmptySide() {
		int bowl = 0;
		
		for( int i=0; i < TOTAL_PITS; i++ ) {
			
			if ( pits.get(i).getType() == PitType.BIG) {
				pits.get(i).addStones(bowl);
				bowl =0;
			}
			else {
				bowl+= pits.get(i).getAllStones();
			}
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
					      +  " title='Number of Stones: " + pits.get(i).countStones()  + "'"
					      +  " style='left: " + ((pits.get(i).getXPosition() * PIT_SIZE) + PIT_SIZE) + "px; "
					     	 	  + " top: "  + ((pits.get(i).getYPosition() * PIT_SIZE) ) + "px;'> \n");
		}
		return rtn; 
	}
	
	public String printStones() {
		String rtn = "";
		for( int i=0; i < TOTAL_PITS; i++) {			
			for(int j=0; j < pits.get(i).countStones(); j++) {
				rtn = rtn.concat("<img src= 'images/roundstone.png' "  
				      +  " class='stone' "
				      +  " onclick='pick(" + i +")' "  
				      +  " style='left: " + ((pits.get(i).getXPosition() * PIT_SIZE) + PIT_SIZE + (j%3 * 10) + 30) + "px; "
				     	 	  + " top: "  + ((pits.get(i).getYPosition() * PIT_SIZE) + (j/3 * 10) + 30 ) + "px;'> \n"); 
			}
		}
		return rtn;
	}

	
	
	

}
