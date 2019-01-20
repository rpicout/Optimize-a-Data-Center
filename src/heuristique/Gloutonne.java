package heuristique;

import main.Data;
import model.Serveur;
import model.Slot;

public class Gloutonne {
	
	/**
	 * On trie le tableau les capacités des serveurs par ordre décroissant
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacités triées par ordre décroissant
	 */
	public int[] getTriServeurCapacite(Data data){
		
		System.out.println("Début tri");
		
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
		System.out.println("Fin tri");
		return triServeur;
	}
	
	
	/**
	 * Méthode calculant la solution de la méthode gloutonne
	 * 
	 * @param data
	 * @return la solution de la méthode gloutonne
	 */
	public Data getSolution(Data data){
		
		System.out.println("Début méthode gloutonne");
		
		int[] triCapacite = getTriServeurCapacite(data);
		Serveur serveur = null;
		Slot slot = null;
		
		for (int i = 0; i < data.getNbServeur(); i++){
			
			serveur = data.getServeurs(triCapacite[i]);
			boolean add = false;
			
			//On ajoute le serveur à un emplacement
			int r = 0;
			int s = 0;
			while (!add && r < data.getNbRow() && s < data.getNbSlot()){
				
				if (serveur.getTaille() + s <= data.getNbSlot()){
					
					// On vérifie que les slots qui suivent sont disponibles
					int k = s;
					boolean ispossible = true;
					if (serveur.getTaille() == 1 && k > 97) {
						
						while (ispossible && k < serveur.getTaille() + s) {
							slot = data.getRow(r).getSlot(k);
							if (slot.getServeur() != null || slot.isIndispo()){
								ispossible = false;
							}
							k++;
						}
					}
					else {
						while (ispossible && k < serveur.getTaille() + s) {
							slot = data.getRow(r).getSlot(k);
							if (slot.getServeur() != null || slot.isIndispo()){
								ispossible = false;
							}
							k++;
						}
					}
					// Si il est possible d'ajouter le serveur, on l'ajoute
					if (ispossible) {
						for (int j = s; j < serveur.getTaille() + s; j++) {
							slot = data.getRow(r).getSlot(j);
							slot.setServeur(serveur);
						}
						add = true;
					}
					//System.out.println(triCapacite[i] + " ajouté " + serveur.getCapacite() + " " + serveur.getTaille());
				}
				
				if (!add) {
					s++;
					if (s == data.getNbSlot()) {
						s = 0;
						r ++;
					}
				}	
				
			}
		}
		
		int numPool = 0;
		for (int r = 0; r < data.getNbRow(); r++){
			for (int s = 0; s < data.getNbSlot(); s++){
				
				serveur = data.getRow(r).getSlot(s).getServeur();
				if (serveur != null && serveur.getPoule() == -1) {
					
					if (numPool == data.getNbPoule() - 1)
						numPool = 0;
					
					serveur.setPoule(numPool);
					data.getPoule(numPool).addServeur(serveur);
					numPool++;
					
				}
				
			}
		}
		
		// TODO A supprimer 
		//Permet d'afficher dans la console à quoi "ressemble" le data center 
		for (int r = 0; r < data.getNbRow(); r++){
			for (int s = 0; s < data.getNbSlot(); s++){
				if (data.getRow(r).getSlot(s).isIndispo())
					System.out.print(".. ");
				else {
					if (data.getRow(r).getSlot(s).getServeur() != null)
						System.out.print(data.getRow(r).getSlot(s).getServeur().getNumero() + " ");
					else 
						System.out.print("** ");
				}
			}
			System.out.println("");
		}
		
		System.out.println("Fin méthode gloutonne");
		return data;
	}

}
