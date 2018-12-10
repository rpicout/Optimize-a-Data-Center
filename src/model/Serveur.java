package model;

public class Serveur {
	
	private int capacite;
	private int taille;
	
	
	public Serveur(int capacite, int taille) {
		super();
		this.capacite = capacite;
		this.taille = taille;
	}
	
	
	public int getCapacite() {
		return capacite;
	}
	
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	
	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}	

}
