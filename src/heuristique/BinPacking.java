package heuristique;


import java.util.Arrays;
import java.util.Collections;

import main.Data;
import model.Serveur;

public class BinPacking {

	
	/**
	 * On tri les serveurs en ordre décroissant de nb d’emplacement qu’il occupe
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacités triées par ordre décroissant
	 */
	public int[] getTriServeurTaille(Data data){
		
		Serveur[] serveurs = data.getServeurs();
		int[] triServeur = new int[data.getNbServeur()]; 
		int numTailleMax;
		int tailleMax;
		
		for (int i = 0; i < data.getNbServeur(); i++){
			triServeur[i] = i;
		}
		
		for (int i = data.getNbServeur() - 1; i >= 0; i--) {
			
			numTailleMax = 0;
			
			for (int j = 0; j <= i; j++) {
				if (serveurs[triServeur[j]].getTaille() < serveurs[triServeur[numTailleMax]].getTaille()) {
					numTailleMax = j;
				}
			}
			
			tailleMax = triServeur[numTailleMax];
			triServeur[numTailleMax] = triServeur[i];
			triServeur[i] = tailleMax;
			//TODO a supprimer
			System.out.println(serveurs[triServeur[i]].getTaille() +", "+triServeur[i]);
		}
		
		return triServeur;
	}
	
	
	/**
	 * 
	 * @param data
	 * @return la solution de la méthode gloutonne
	 */
	public Data getSolution(Data data){
		
		int[] triServeurTaille = getTriServeurTaille(data);
		
		/**
		 * NbRow =0
				Espace_dispo[nbRow]
				Pour i allant de 1 à nbRow
					Si on ajoute le serveur j dans la rangée r Faire
				Calculer l'espace qu'il restera
				Fin si
					Garder le numéro de la ligne qui aura le plus petit espace en reste
					Si plusieurs Alors 
				On choisit aléatoirement 
					Fin si
				Fin pour
				Ajouter le serveur j dans la rangée r retenue
				Pour i allant de 1 à nbRow
					Si on ajoute le serveur j dans la pool p Faire 
				Calculer le nb de slots déjà affectés à ce pool
					Fin si
					Garder le numéro de pool qui aura le plus petit nb de slots affectés
				Si plusieurs Alors 
				On choisit aléatoirement
					Fin si 
				Fin pour
				Ajouter le serveur j dans la pool p sélectionnée

**/
		
		return null;
	}
	
}
