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
	 * 
	 * @param data
	 * @return la solution de la méthode gloutonne
	 */
	public Data getSolution(Data data){
		
		int[] triCapacite = getTriServeurCapacite(data);
		
		for (int i = 0; i < data.getNbServeur(); i++){
			
			Serveur serveur = data.getServeurs(triCapacite[i]);
			boolean add = false;
			
			//On ajoute le serveur à un emplacement
			while (!add){
				for (int r = 0; r < data.getNbRow(); r++){
					for (int s = 0; s < data.getNbSlot(); s++){
						
						Slot slot = data.getRow(r).getSlot(s);
						
						if (slot.getServeur() == null){
							if (serveur.getTaille() + s <= data.getNbSlot()){
								for (int k = s; k < serveur.getTaille() + s; k++){
									data.getRow(r).getSlot(k).setServeur(serveur);
								}
								add = true;
							}
						}
						
					}
				}
			}
			
			/*
			 *  int numPool = 0 ;
				Pour r allant de 1 à nbRangées Faire
				Pour s allant de 1 à nbSlots Faire
						Si serveur n’est pas affecté à un pool
							Si numPool = nbPool-1
								numPool = 0 ;
							Fin Si
							Affecter le serveur à l’emplacement [r,s] à la pool numPool
							numPool ++
						Fin si
					Fin pour
				Fin pour
				Calculer la plus petite capacité garantie de tous les groupes

			 */
			
		}
		
		return null;
	}

}
