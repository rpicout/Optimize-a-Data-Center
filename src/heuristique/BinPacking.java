package heuristique;


import java.util.Arrays;
import java.util.Collections;

import main.Data;
import model.Serveur;

public class BinPacking {

	
	/**
	 * On tri les serveurs en ordre d�croissant de nb d�emplacement qu�il occupe
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacit�s tri�es par ordre d�croissant
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
	 * @return la solution de la m�thode gloutonne
	 */
	public Data getSolution(Data data){
		
		int[] triServeurTaille = getTriServeurTaille(data);
		
		/**
		 * NbRow =0
				Espace_dispo[nbRow]
				Pour i allant de 1 � nbRow
					Si on ajoute le serveur j dans la rang�e r Faire
				Calculer l'espace qu'il restera
				Fin si
					Garder le num�ro de la ligne qui aura le plus petit espace en reste
					Si plusieurs Alors 
				On choisit al�atoirement 
					Fin si
				Fin pour
				Ajouter le serveur j dans la rang�e r retenue
				Pour i allant de 1 � nbRow
					Si on ajoute le serveur j dans la pool p Faire 
				Calculer le nb de slots d�j� affect�s � ce pool
					Fin si
					Garder le num�ro de pool qui aura le plus petit nb de slots affect�s
				Si plusieurs Alors 
				On choisit al�atoirement
					Fin si 
				Fin pour
				Ajouter le serveur j dans la pool p s�lectionn�e

**/
		
		return null;
	}
	
}
