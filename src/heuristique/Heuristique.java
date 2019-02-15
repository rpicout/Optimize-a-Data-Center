package heuristique;

import java.util.ArrayList;
import java.util.List;

import main.Data;
import model.Serveur;
import model.Slot;

public class Heuristique {

	// On tri les serveur en capacit� d�croissante et taille croissante pour les
	// mettre dans les ranger pour avoir le plus de capacit� posible

	/**
	 * On trie le tableau les capacit�s des serveurs par ordre d�croissant
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacit�s tri�es par ordre
	 *         d�croissant
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

		// Maintenant que les c'est tri� par capacit� il faut trier par taille �
		// "l'int�rieur" d'une meme capacit�

		// TODO � terminer !!
		int currentCapacite = serveurs[triServeur[0]].getCapacite();
		List<Serveur> capaConstante = new ArrayList<Serveur>();
		int indiceTailleMax, tailleMax;
		boolean next = false;
		boolean finCalcul = false;
		List<Integer> indiceAjouter = new ArrayList<Integer>(); 

		for (int i = 0; i < data.getNbServeur() ; i++) {

			finCalcul = false;
			int iterator = i;

			while (!finCalcul) {
				next = false;
				while (!next) { 
					if (!indiceAjouter.contains(iterator)) {
						//TODO toujours pb de outOfBound 625...
						if (serveurs[triServeur[iterator]].getCapacite() == currentCapacite) {
							indiceAjouter.add(iterator);
							capaConstante.add(data.getServeurs(triServeur[iterator]));
							iterator ++;
						} else {
							currentCapacite = serveurs[triServeur[iterator]].getCapacite();
							indiceAjouter.clear();
							next = true;
						}
					}
				}

				for (int j = capaConstante.size()-1 ; j >= 0; j--) {
					indiceTailleMax = 0;

					for (int k = 0; k < j; k++) {
						if (capaConstante.get(k).getTaille() > capaConstante.get(indiceTailleMax).getTaille()) {
							indiceTailleMax = k;
						}
					}

					tailleMax = capaConstante.get(indiceTailleMax).getNumero();
					capaConstante.get(indiceTailleMax).setNumero(capaConstante.get(j).getNumero());
					capaConstante.get(j).setNumero(tailleMax);

				}
				int j = 0;
				for (int x = i; x < i - capaConstante.size(); x--) {
					triServeur[x] = capaConstante.get(capaConstante.size() - j).getNumero();
					j++;
				}
				capaConstante.clear();
				finCalcul = true;

			}
		}
		
		return triServeur;
	}
	/**
	 * M�thode calculant la solution de la m�thode heuristique
	 * 
	 * @param data
	 * @return la solution de la m�thode heuristique
	 */
	public Data getSolution(Data data){
		
		System.out.println("D�but m�thode heuristique");
		
		int[] triCapacite = getTriServeurCapaciteTaille(data);
		Serveur serveur = null;
		Slot slot = null;
		
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
					}
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
					
					serveur.setPoule(numPool);
					data.getPoule(numPool).addServeur(serveur);
					numPool++;

					if (numPool == data.getNbPoule())
						numPool = 0;
				}
				
			}
		}
		
		System.out.println("Fin m�thode heuristique");
		return data;
	}


}
