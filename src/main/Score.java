package main;

import java.util.ArrayList;
import java.util.List;

import model.Serveur;

public class Score {

	private int pool;
	private List<Integer> capaciteG;
	
	
	public int getPool() {
		return pool;
	}

	public void setPool(int pool) {
		this.pool = pool;
	}

	public List<Integer> getCapaciteG() {
		return capaciteG;
	}

	public void setCapaciteG(List<Integer> capaciteG) {
		this.capaciteG = capaciteG;
	}

	/**
	 * Méthode qui permet de calculer le score en fonction des résultats que nous avons obtenus
	 * 
	 * On cherche donc à maximiser la plus petite capacité garantie de tous les pools.
	 * 
	 * @param data
	 * @return le score
	 */
	public int calculScore (Data data) {
		
		// On initialise les variables à un très grand integer
		int minCapaciteGarantie = Integer.MAX_VALUE;
		int minCapaciteGarantiePool = Integer.MAX_VALUE;
		int capaGarantie = 0;
		
		int capacitePool;
		int capacitePoolRow;
		Serveur serveur;

		int[] capaRow = new int[data.getNbRow()];
		
		capaciteG = new ArrayList<Integer>();
		
		for (int p = 0; p < data.getNbPoule(); p++) {
			capacitePool = 0;
			for (int m = 0; m < data.getNbServeur(); m++) {
				serveur = data.getServeurs(m);
				if (serveur.getPoule() == p) {
					capacitePool += serveur.getCapacite();
				}
			}
			for (int r = 0; r < data.getNbRow(); r++) {
				
				capacitePoolRow = 0;
				
				for (int m = 0; m < data.getNbServeur(); m++) {
					serveur = data.getServeurs(m);
					if (serveur.getPoule() == p) {
						boolean capaciteAdd = false;
						int s = 0;
						while (!capaciteAdd && s < data.getNbSlot()) {
							if (data.getRow(r).getSlot(s).getServeur() == serveur) {
								capacitePoolRow += serveur.getCapacite();
								capaciteAdd = true;
								capaRow[r]+=serveur.getCapacite();
							}
							s++;
						}
					}
				}
				
				if (minCapaciteGarantie > capacitePool - capacitePoolRow) {
					minCapaciteGarantie = capacitePool - capacitePoolRow;
				}
				
			}
			capaciteG.add(minCapaciteGarantie);
			if (minCapaciteGarantiePool > minCapaciteGarantie) {
				minCapaciteGarantiePool = minCapaciteGarantie;
			}
			if (p == 0) {
				capaGarantie = minCapaciteGarantie;
				pool = p;
			}
			if (minCapaciteGarantie < capaGarantie) {
				capaGarantie = minCapaciteGarantie;
				pool = p;
			}
		}
		
		return minCapaciteGarantiePool;
	}
	
}
