package model;

public class Slot {

	private Serveur serveur;

	
	public Slot(Serveur serveur) {
		super();
		this.serveur = serveur;
	}

	
	public Serveur getServeur() {
		return serveur;
	}

	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}
	
}
