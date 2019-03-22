package gameelements;

/** Something that can hold one or more stones
 * @author ughini
 *
 */
public class StoneHandler extends GameObject {
	
	private static final long serialVersionUID = -9124171067819078502L;

	private Integer stones;
	private Player owner;

	public StoneHandler() {
		stones = 0;
	}
	
	public void setStones( Integer stones) {
		this.stones = stones;
	}
	
	public void addStone() {
		stones++;
	}

	public void addStones(int s) {
		stones+=s;
	}

	public void dropStone() {
		stones--;
		if (stones < 0 )
			stones = 0;
	}

	public Integer getAllStones() {
		Integer temp_stones = stones;
		stones = 0;
		return temp_stones;
	}
	
	public Integer countStones() {
		return stones;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
}
