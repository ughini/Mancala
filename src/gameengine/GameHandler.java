package gameengine;

import gameelements.GameBoard;
import gameelements.Hand;
import gameelements.Pit;
import gameelements.PitType;
import gameelements.Player;

/** Controls all game elements
 * 
 * @author ughini
 *
 */
public class GameHandler {
	
	private GameBoard gameboard;
	private Hand player1, player2, activePlayer;
	private String message = "";
	
	public GameHandler() {
		this.reset();
	}

	//Restarts the game
	public void reset() {
		gameboard = new GameBoard();
		player1 = new Hand(Player.PLAYER1, "Player 1");
		player2 = new Hand(Player.PLAYER2, "Player 2");
		activePlayer = player1;
		this.message = "New Game! " + activePlayer.getName() + " turn!" ;
	}
	
	
	public GameBoard getGameBoard() {
		return this.gameboard;
	}
	
	//Return Game messages to display
	public String getMessage() {
		return this.message;
	}
	
	//Gets all the stones of a pit and put then on players hand
	//Checks if the current player can click it
	public void clickPit( int number) {
		this.message = "";
		
		Pit pit =  gameboard.getPit(number);
		
		if ( pit == null) {
			this.message = "The pit number selected does not exists!";
			return;
		}
		
		if ( pit.getType() == PitType.BIG) {
			this.message = "You can't get the stones from the big pits!";
			return;			
		}
		
		if ( pit.countStones() == 0) {
			this.message = "You can't click on a empty Pit!";
			return;			
		}
		
		if ( pit.getOwner() != activePlayer.getOwner()) {
			this.message = "You can't get the stones of your opponent!";
			return;			
		}
		
		//Get All stones from the clicked pit
		activePlayer.setStones(pit.getAllStones());
		
		//Drop one stone on each subsequent pit
		while( activePlayer.countStones() > 0 ) {
			pit = gameboard.getNextPit(pit);
			
			if ( pit.getOwner() == activePlayer.getOwner() || pit.getType() == PitType.SMALL) {
				pit.addStone();
				activePlayer.dropStone();
			}
		}
		
		//Player plays again
		if (pit.getType() == PitType.BIG) {
			this.message = "Last stone landed at Big Pit. " + activePlayer.getName() + " plays again!";
			return;
		}
		
		//Player gets all the opposite pit stones to his big pit
		if (pit.countStones() == 1 
				&& pit.getOwner() == activePlayer.getOwner() ) {
			Pit bigpit = gameboard.getPlayerBigPit(activePlayer.getOwner());
			bigpit.addStones(pit.getAllStones());
			bigpit.addStones(gameboard.getOppositePit(pit).getAllStones());						
			this.message = "Last stone landed at empty own Pit. " + activePlayer.getName() + " gets all opposite pit stones!";
		}
		
		//Checks End game condition
		if (gameboard.emptySide()) {
			gameboard.cleanNotEmptySide();
			int p1 = gameboard.getPlayerBigPit(Player.PLAYER1).countStones();
			int p2 = gameboard.getPlayerBigPit(Player.PLAYER2).countStones();
			
			this.message = "The Game as Ended!\n" 
					+ player1.getName() + " got " + p1 + " stones.\n"
					+ player2.getName() + " got " + p2 + " stones.\n"
					+ (p1 == p2 ? "Game ended tided!" : (p1 > p2 ? player1.getName() : player2.getName()) + " WINS!" );
			return;
		}
				
		if (activePlayer == player1) {
			activePlayer = player2;
		}
		else {
			activePlayer = player1;
		}
		this.message = this.message.concat("\n" + activePlayer.getName() + " turn!");
	}
	
}
