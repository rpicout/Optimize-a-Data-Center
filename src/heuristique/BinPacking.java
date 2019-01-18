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
			//System.out.println(serveurs[triServeur[i]].getTaille() +", "+triServeur[i]);
			
		}
		
		return triServeur;
	}
	
	
	/**
	 * Méthode calculant la solution de la méthode BinPacking
	 * 
	 * @param data
	 * @return la solution de la méthode BinPacking
	 */
	public Data getSolution(Data data){
		
		int[] triServeurTaille = getTriServeurTaille(data);
		
		Serveur serveur = null;
		int sd;
		int disp;
		int espaceResiduel = 0;
		int espaceRetenue = data.getNbSlot()+1; 
		int rangeRetenue = 0;
		for(int i = 0; i< data.getNbServeur(); i++) {
			
			serveur = data.getServeurs(triServeurTaille[i]);
			
			
			for(int j =0 ; j < data.getNbRow(); j++) {
				sd = 0;
				disp = data.getNbSlot();
				
				for( int k = 0 ; k < data.getNbSlot(); k++) {
					if(data.getRow(j).getSlot(k).isIndispo() == true || data.getRow(j).getSlot(k).getServeur() != null) {
						sd++;
					}
				}
				//System.out.println("il y a " +sd+" slot indisponible sur la rangé " + j);//TODO
				disp = disp - sd;	
				if(disp >= serveur.getTaille()) { //TODO verifier que les slot encore dispo sont à coté !! sinon on ne peut pas ajouter le serveur
					espaceResiduel = (disp - serveur.getTaille());
					//System.out.println("Si on met le serveur " + i +" sur la rangé " + j+ ", alors il restera " + espaceResiduel + " slot dispo");//TODO
					if(espaceResiduel < espaceRetenue) {
						espaceRetenue = espaceResiduel;
						rangeRetenue = j;
					}
				}else {
					//System.out.println("pas asser d'espace sur la rangé "+j);//TODO
				}
					
			}
			espaceRetenue = data.getNbSlot()+1; 
			//System.out.println("la rangé retenue est la "+ rangeRetenue);//TODO
			boolean add = false;
			
			//On ajoute le serveur à un emplacement
			int r = rangeRetenue;
			int s = 0;
			while (!add && r < data.getNbRow() && s < data.getNbSlot()){
				Slot slot = data.getRow(r).getSlot(s);
						
				if (slot.getServeur() == null && !slot.isIndispo()){
					if (serveur.getTaille() + s < data.getNbSlot()){
						for (int k = s; k < serveur.getTaille() + s; k++){
							data.getRow(r).getSlot(k).setServeur(serveur);
						}
						add = true;
						System.out.println("le serveur " + i + " à été ajouté à la rangé " + r);//TODO
						 
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
