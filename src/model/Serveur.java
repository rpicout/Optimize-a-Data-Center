package model;

public class Serveur {
	
	private int numero;
	private int capacite;
	private int taille;
	private int poule;
	
	
	public Serveur(int numero, int taille, int capacite) {
		super();
		this.numero = numero;
		this.capacite = capacite;
		this.taille = taille;
		this.poule = -1;
	}


	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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


	public int getPoule() {
		return poule;
	}

	public void setPoule(int poule) {
		this.poule = poule;
	}	
	
}
