package gameelements;

public class Position {
	private int x;
	private int y;
	
	public static int BOTTOM = 3; 
	public static int CENTER = 2;
	public static int TOP =1;
		
	
	public void set( int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
}
