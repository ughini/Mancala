package gameengine;

import gameelements.GameBoard;
import gameelements.Hand;
import gameelements.Player;

/** Controls all game elements
 * 
 * @author ughini
 *
 */
public class GameHandler {
	
	private GameBoard gameboard;
	private Hand player1, player2;
	
	public GameHandler() {
		this.reset();
	}

	public void reset() {
		gameboard = new GameBoard();
		player1 = new Hand(Player.PLAYER1);
		player2 = new Hand(Player.PLAYER2);
	}
	
	public GameBoard getGameBoard() {
		return this.gameboard;
	}
	
	
}
