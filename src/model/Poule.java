package model;

import java.util.ArrayList;
import java.util.List;

public class Poule {

	List<Serveur> serveurs; 
	
	
	public Poule () {
		serveurs = new ArrayList<Serveur>();
	}


	public List<Serveur> getServeurs() {
		return serveurs;
	}
	
	public Serveur getServeurs(int i) {
		return serveurs.get(i);
	}

	public void setServeurs(List<Serveur> serveurs) {
		this.serveurs = serveurs;
	}
	
	public void addServeur(Serveur serveur) {
		this.serveurs.add(serveur);
	}
	
}
