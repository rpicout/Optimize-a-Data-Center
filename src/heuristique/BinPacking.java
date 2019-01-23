package heuristique;

import java.util.Arrays;
import java.util.Collections;

import main.Data;
import model.Row;
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
			// TODO a supprimer
			// System.out.println(serveurs[triServeur[i]].getTaille() +", "+triServeur[i]);

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
				//System.out.println("il y a " +sd+" slot indisponible sur la rangé " + j);//TODO
				disp = disp - sd;
				if (disp >= serveur.getTaille()) { 
					espaceResiduel = (disp - serveur.getTaille());
					// System.out.println("Si on met le serveur " + i +" sur la rangé " + j+ ",
					// alors il restera " + espaceResiduel + " slot dispo");//TODO
					if (espaceResiduel < espaceRetenue) {
						espaceRetenue = espaceResiduel;
						rangeRetenue = j;
					}
				} else {
					// System.out.println("pas asser d'espace sur la rangé "+j);//TODO
				}

			}
			espaceRetenue = data.getNbSlot() + 1;
			// System.out.println("la rangé retenue est la "+ rangeRetenue);//TODO
			boolean add = false;

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
					}
					//System.out.println(triCapacite[i] + " ajouté " + serveur.getCapacite() + " " + serveur.getTaille());
				}

				if (!add) { // J'ai laissée cette condition car je ne vérifie pas si les slots encore dispo
							// d'une rangé sont a coté ou pas
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
			int p = data.getNbPoule(); 
			int poolRetenu = 0;
			for(int j = 0 ; j < data.getNbPoule(); j++){
				if(data.getPoule(j).getServeurs().size() + 1 < p) {
					p = data.getPoule(j).getServeurs().size();
					poolRetenu = j;
					//System.out.println("p :" + p);//TODO
				}
				//System.out.println("Poule " + data.getPoule(j).getServeurs().size());//TODO
			}
			//On ajoute au pool retenu
			serveur.setPoule(poolRetenu);
			data.getPoule(poolRetenu).addServeur(serveur);
			//System.out.println("le serveur " + i + " est ajouter au pool " + poolRetenu);//TODO

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


		return data;
	}

}
