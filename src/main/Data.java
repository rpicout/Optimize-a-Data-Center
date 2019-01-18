package main;

import model.Coord;
import model.Poule;
import model.Row;
import model.Serveur;

public class Data {

	private int nbRow;
	private int nbSlot;
	private int nbSlotIndispo;
	private int nbPoule;
	private int nbServeur;
	
	private Coord[] coordSlotIndispo;
	private Serveur[] serveurs; // attention a l'indice zéro c'est le serveur numéro 1 ! bisous
	private Poule[] poule;
	private Row[] row;
	
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
		this.serveurs = new Serveur[nbServeur];
		this.poule = new Poule[nbPoule];
		this.row = new Row[nbRow];
		
		for (int i = 0; i < nbRow; i++) {
			this.row[i] = new Row(nbSlot);
		}
		
		for (int i = 0; i < nbPoule; i++) {
			this.poule[i] = new Poule();
		}
	}
	
	/**
	 * Getters et Setters
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

	/**
	 * Méthode add serveur dans le tableau serveurs
	 */
	public void setServeurs(int i, Serveur serveurs) {
		this.serveurs[i] = serveurs;
	}
	
	
	public Poule[] getPoule() {
		return poule;
	}
	
	public Poule getPoule(int i) {
		return poule[i];
	}

	public void setPoule(Poule[] poule) {
		this.poule = poule;
	}
	
	public void setPoule(int i, Poule poule) {
		this.poule[i] = poule;
	}

	
	public Row[] getRow() {
		return row;
	}
	
	public Row getRow(int i){
		return row[i];
	}

	public void setRow(Row[] row) {
		this.row = row;
	}
	
	public void setRow(int i, Row row){
		this.row[i] = row;
	}
}

