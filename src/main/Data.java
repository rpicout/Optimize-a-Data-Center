package main;

import model.Coord;
import model.Serveur;

public class Data {

	private int nbRow;
	private int nbSlot;
	private int nbSlotIndispo;
	private int nbPoule;
	private int nbServeur;
	
	private Coord[] coordSlotIndispo;
	private Serveur[] serveurs; // attention a l'indice zéro c'est le serveur numéro 1 ! bisous
	
	/**
	 * Constructeur
	 */
	
	public Data(int nbRow, int nbSlot, int nbSlotIndispo, int nbPoule, int nbServeur) {
		this.nbRow = nbRow;
		this.nbSlot = nbSlot;
		this.nbSlotIndispo = nbSlotIndispo;
		this.nbPoule = nbPoule;
		this.nbServeur = nbServeur;
		
		coordSlotIndispo = new Coord[nbSlotIndispo];
		
		serveurs = new Serveur[nbServeur]; 

	}
	
	/**
	 * Getter et Setter
	 */

	public int getNbRow() {
		return nbRow;
	}

	public void setNbRow(int nbRow) {
		this.nbRow = nbRow;
	}

	public int getNbSlot() {
		return nbSlot;
	}

	public void setNbSlot(int nbSlot) {
		this.nbSlot = nbSlot;
	}

	public int getNbSlotIndispo() {
		return nbSlotIndispo;
	}

	public void setNbSlotIndispo(int nbSlotIndispo) {
		this.nbSlotIndispo = nbSlotIndispo;
	}

	public int getNbPoule() {
		return nbPoule;
	}

	public void setNbPoule(int nbPoule) {
		this.nbPoule = nbPoule;
	}

	public int getNbServeur() {
		return nbServeur;
	}

	public void setNbServeur(int nbServeur) {
		this.nbServeur = nbServeur;
	}

	public Coord[] getCoordSlotIndispo() {
		return coordSlotIndispo;
	}
	
	public Coord getCoordSlotIndispo(int i) {
		return coordSlotIndispo[i];
	}
	
	public void setCoordSlotIndispo(Coord[] coordSlotIndispo) {
		this.coordSlotIndispo = coordSlotIndispo;
	}

	public void setCoordSlotIndispo(int i, Coord coordSlotIndispo) {
		this.coordSlotIndispo[i] = coordSlotIndispo;
	}

	public Serveur[] getServeurs() {
		return serveurs;
	}
	
	public Serveur getServeurs(int i) {
		return serveurs[i];
	}

	public void setServeurs(Serveur[] serveurs) {
		this.serveurs = serveurs;
	}
	
	public void setServeurs(int i, Serveur serveurs) {
		this.serveurs[i] = serveurs;
	}
	
	/**
	 * Méthode add serveur dans le tableau serveurs
	 */
	
	public void addServeur(Serveur serveur, int i) {
		serveurs[i] = serveur;
	}
	
	
}

