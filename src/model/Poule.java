package model;

import java.util.ArrayList;
import java.util.List;

public class Poule {

	List<Serveur> serveurs; 
	
	
	public Poule () {
		serveurs = new ArrayList<Serveur>();
	}


	public List<Serveur> getSlots() {
		return serveurs;
	}
	
	public Serveur getSlots(int i) {
		return serveurs.get(i);
	}

	public void setSlots(List<Serveur> serveurs) {
		this.serveurs = serveurs;
	}
	
}
