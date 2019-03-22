package gameelements;

import java.io.Serializable;

/** General Game Object with a position on screen
 */ 
public class GameObject implements Serializable {
	private static final long serialVersionUID = 8253435122929506898L;
	
	private Position position;
	private String imageFile;
	
	public GameObject() {
		position = new Position();
	}
	
	public void setPosition( Integer x, Integer y) {
		position.set(x, y);
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Integer getXPosition() {
		return position.getX();
	}
	
	public Integer getYPosition() {
		return position.getY();
	}
	
	public void setYPosition(Integer y) {
		position.setY(y);
	}


	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
}
