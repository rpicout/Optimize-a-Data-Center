package model;

public class Coord {
	
	//Je pense qu'il y a moyen de supprimer cette classe en mettant un boolean indispo dans la classe Slot
	
	private int x;
	private int y;
	
	/**
	 * Constructeur
	 * @param x
	 * @param y
	 */
	
	public Coord(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Getter et Setter
	 * 
	 */

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

}
