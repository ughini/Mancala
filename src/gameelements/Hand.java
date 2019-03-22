package gameelements;

/** A player Hand that gets and puts Stones at pits.
 * 
 */

public class Hand extends StoneHandler {

	private static final long serialVersionUID = -1936699590347650474L;
	
	public Hand(Player owner) {
		super();
		this.setOwner(owner);
	}
	
		
}
