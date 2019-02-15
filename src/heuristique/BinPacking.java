package heuristique;

import main.Data;
import model.Serveur;
import model.Slot;

public class BinPacking {

	/**
	 * On tri les serveurs en ordre décroissant de nb d’emplacement qu’il occupe
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacités triées par ordre
	 *         décroissant
	 */
	public int[] getTriServeurTaille(Data data) {

		Serveur[] serveurs = data.getServeurs();
		int[] triServeur = new int[data.getNbServeur()];
		int numTailleMax;
		int tailleMax;

		for (int i = 0; i < data.getNbServeur(); i++) {
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

		}

		return triServeur;
	}


	/**
	 * Méthode calculant la solution de la méthode BinPacking
	 * 
	 * @param data
	 * @return la solution de la méthode BinPacking
	 */
	public Data getSolution(Data data) {
		System.out.println("Début méthode BinPacking");
		int[] triServeurTaille = getTriServeurTaille(data);

		Serveur serveur = null;
		int sd;
		int disp;
		int espaceResiduel = 0;
		int espaceRetenue = data.getNbSlot() + 1;
		int rangeRetenue = 0;
		
		for (int i = 0; i < data.getNbServeur(); i++) {

			serveur = data.getServeurs(triServeurTaille[i]);

			for (int j = 0; j < data.getNbRow(); j++) {
				sd = 0;
				disp = data.getNbSlot();

				for (int k = 0; k < data.getNbSlot(); k++) {
					if (data.getRow(j).getSlot(k).isIndispo() == true || data.getRow(j).getSlot(k).getServeur() != null) {
						sd++;
					}
				}
				disp = disp - sd;
				if (disp >= serveur.getTaille()) { 
					espaceResiduel = (disp - serveur.getTaille());
					if (espaceResiduel < espaceRetenue) {
						espaceRetenue = espaceResiduel;
						rangeRetenue = j;
					}
				}

			}
			espaceRetenue = data.getNbSlot() + 1;
			boolean add = false;
			boolean ajouter = false;

			// On ajoute le serveur à un emplacement
			int r = rangeRetenue;
			int s = 0;
			while (!add &&  s < data.getNbSlot()) {
				
				if (serveur.getTaille() + s <= data.getNbSlot()){
					Slot slot;
					
					// On vérifie que les slots qui suivent sont disponibles
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
						ajouter = true;
					}
				}

				if (!add) {
					s++;
					if (s == data.getNbSlot()) {
						s = 0;
						r++;
						if (r == data.getNbRow())
							r = 0;
						if (r == rangeRetenue)
							add = true;
					}
				}

			}
			if (ajouter) {
				int p = Integer.MAX_VALUE; 
				int poolRetenu = 0;
				for(int j = 0 ; j < data.getNbPoule(); j++){
					if(data.getPoule(j).getServeurs().size() + 1 < p) {
						p = data.getPoule(j).getServeurs().size();
						poolRetenu = j;
					}
				}
				//On ajoute au pool retenu
				serveur.setPoule(poolRetenu);
				data.getPoule(poolRetenu).addServeur(serveur);
			}

		}
		
		System.out.println("Fin méthode BinPacking");
		return data;
	}

}
