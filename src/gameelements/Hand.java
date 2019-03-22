package gameelements;

/** A player Hand that gets and puts Stones at pits.
 * 
 */

public class Hand extends StoneHandler {

	private static final long serialVersionUID = -1936699590347650474L;
	
	private String name;
	
	public Hand(Player owner, String name) {
		super();
		this.setOwner(owner);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
		
}
