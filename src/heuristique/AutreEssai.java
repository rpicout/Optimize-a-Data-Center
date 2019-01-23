package heuristique;

import main.Data;
import model.Serveur;

public class AutreEssai {

	// On tri les serveur en capacité décroissante et taille croissante pour les
	// mettre dans les ranger pour avoir le plus de capacité posible

	/**
	 * On trie le tableau les capacités des serveurs par ordre décroissant
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacités triées par ordre
	 *         décroissant
	 */
	public int[] getTriServeurCapaciteTaille(Data data) {

		Serveur[] serveurs = data.getServeurs();
		int[] triServeur = new int[data.getNbServeur()];
		int numTailleMax;
		int capaciteMax;

		for (int i = 0; i < data.getNbServeur(); i++) {
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

		// Maintenant que les c'est trié par capacité il faut trier par taille à
		// "l'intérieur" d'une meme capacité
		
	//TODO à terminer !! 	
		int curentCapacite = serveurs[triServeur[0]].getCapacite();
		boolean next = false;
		int a = 0 ; 
		int k= 0;
			while(serveurs[triServeur[k]].getCapacite() == curentCapacite) {
				a++;
				k++;
			}
			for(int i = 0 ; i < triServeur.length; i++) {
			
			for( int j = i ; j < i+a ; j++) {
				
			}
			
			
			if(serveurs[triServeur[i]].getCapacite() == curentCapacite) {
				a++;
			}else {
				curentCapacite = serveurs[triServeur[i]].getCapacite();
				a = 0;
			}
			
			
			
			
			//System.out.println("Numero de serveur : " + triServeur[i] + ", Capacité : " + serveurs[triServeur[i]].getCapacite() + ", Taille : " + serveurs[triServeur[i]].getTaille());
		}
		return triServeur;
	}

}
