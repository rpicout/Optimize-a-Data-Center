package heuristique;

import java.util.ArrayList;
import java.util.List;

import main.Data;
import model.Serveur;
import model.Slot;

public class RowEquivalente {
	
	/*
	 * Ajouter les serveurs par ordre d�croissant de capacit� aux slots
	 * Prendre le serveur ayant la plus grande capacit� et le serveur ayant la plus petite et les ajouter � la pool i 

	 * -> Le but est de faire en sorte que la somme des capacit�s dans chacun des pools soit � peu pr�s �quivalent et pareil pour les rang�es 

	 * Ensuite, il restera � faire une m�ta-heuristique comme celle de la recherche locale mais qui va seulement changer l'indice de la pool 
	 * pour deux serveurs si le score obtenu est meilleur que celui d'avant
	 */

	private List<Integer> serverOnCenter = new ArrayList<Integer>();
	private List<Integer> serverOnCenter2 = new ArrayList<Integer>();
	
	private List<List<Integer>> serverOnEachRow = new ArrayList<>();
	
	public List<List<Integer>> getServerOnEachRow() {
		return serverOnEachRow;
	}

	public void setServerOnEachRow(List<List<Integer>> serverOnEachRow) {
		this.serverOnEachRow = serverOnEachRow;
	}

	public List<Integer> getServerOnCenter() {
		return serverOnCenter;
	}

	public void setServerOnCenter(List<Integer> serverOnCenter) {
		this.serverOnCenter = serverOnCenter;
	}

	/**
	 * On trie le tableau les capacit�s des serveurs par ordre d�croissant
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacit�s tri�es par ordre d�croissant
	 */
	public int[] getTriServeurCapacite(Data data){
		
		Serveur[] serveurs = data.getServeurs();
		int[] triServeur = new int[data.getNbServeur()]; 
		int numTailleMax;
		int capaciteMax;
		
		
		for (int i = 0; i < data.getNbServeur(); i++){
			triServeur[i] = i;
		}
		
		for (int i = data.getNbServeur() - 1; i >= 0; i--) {
			
			numTailleMax = 0;
			
			for (int j = 0; j <= i; j++) {
				if (serveurs[triServeur[j]].getCapacite() < serveurs[triServeur[numTailleMax]].getCapacite()) {
					numTailleMax = j;
				}
			}
			
			capaciteMax = triServeur[numTailleMax];
			triServeur[numTailleMax] = triServeur[i];
			triServeur[i] = capaciteMax;
			
		}

		return triServeur;
	}
	
	/**
	 * M�thode calculant la solution de la m�thode gloutonne
	 * 
	 * @param data
	 * @return la solution de la m�thode gloutonne
	 */
	public Data getSolution(Data data){
		
		System.out.println("D�but m�thode rowEquivalentes");
		
		int[] triCapacite = getTriServeurCapacite(data);
		Serveur serveur = null;
		Slot slot = null;
		

		for (int i = 0; i < data.getNbRow(); i++) {
			serverOnEachRow.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < data.getNbServeur(); i++){
			
			serveur = data.getServeurs(triCapacite[i]);
			boolean add = false;
			
			//On ajoute le serveur � un emplacement
			int r = 0;
			int s = 0;
			while (!add && r < data.getNbRow() && s < data.getNbSlot()){
				
				if (serveur.getTaille() + s <= data.getNbSlot()){
					
					// On v�rifie que les slots qui suivent sont disponibles
					int k = s;
					boolean ispossible = true;
					while (ispossible && k < serveur.getTaille() + s) {
						slot = data.getRow(r).getSlot(k);
						if (slot.getServeur() != null || slot.isIndispo()){
							ispossible = false;
						}
						k++;
					}
					
					// Si il est possible d'ajouter le serveur, on l'ajoute
					if (ispossible) {
						for (int j = s; j < serveur.getTaille() + s; j++) {
							slot = data.getRow(r).getSlot(j);
							slot.setServeur(serveur);
						}
						add = true;
						serverOnCenter.add(serveur.getNumero());
						serverOnCenter2.add(serveur.getNumero());
						serverOnEachRow.get(r).add(serveur.getNumero());
					}
				}
				
				if (!add) {
					r++;
					if (r == data.getNbRow()) {
						r = 0;
						s ++;
					}
				}	
				
			}
		}
		
		int numPool = 0;
		for (int r = 0; r < data.getNbRow(); r++){
			for (int s = 0; s < data.getNbSlot(); s++){
				
				serveur = data.getRow(r).getSlot(s).getServeur();
				if (serveur != null && serveur.getPoule() == -1) {
					
					serveur.setPoule(numPool);
					data.getPoule(numPool).addServeur(serveur);
					numPool++;

					if (numPool == data.getNbPoule())
						numPool = 0;
				}
				
			}
		}

		System.out.println("Fin m�thode rowEquivalentes");
		return data;
	}
	
	
}
