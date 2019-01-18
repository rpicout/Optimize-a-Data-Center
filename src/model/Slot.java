package model;

public class Slot {

	private Serveur serveur;
	private boolean indispo;

	
	public Slot(Serveur serveur, boolean indispo) {
		super();
		this.serveur = serveur;
		this.indispo = indispo;
	}

	
	public Serveur getServeur() {
		return serveur;
	}

	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}


	public boolean isIndispo() {
		return indispo;
	}

	public void setIndispo(boolean indispo) {
		this.indispo = indispo;
	}
	
}
