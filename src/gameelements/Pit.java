package gameelements;

/** A pit on the board
 * 
 */
public class Pit extends StoneHandler {
	
	private static final long serialVersionUID = 4548002924410296080L;

	PitType type;
	
	public Pit(Player owner, PitType type) {  
		super();
		this.setImageFile("images/woodHole.png");
		this.setOwner(owner);
		this.setType(type);
	}
	
	public PitType getType() {
		return this.type;
	}

	public void setType(PitType type) {
		this.type = type;
	}
	
}
