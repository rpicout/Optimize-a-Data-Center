package heuristique;


import java.util.Arrays;
import java.util.Collections;

import main.Data;
import model.Serveur;

public class BinPacking {

	// On tri les serveurs en ordre décroissant de nb d’emplacement qu’il occupe
	public Integer[] triServeur(Data data) {

		Integer[] tri = new Integer[data.getNbServeur()];
		Serveur[] serveur = new Serveur[data.getNbServeur()];
		serveur = data.getServeurs();

		for (int i = 0; i < tri.length; i++) {
			tri[i] = serveur[i].getTaille();
		}
		
		
		Arrays.sort(tri, Collections.reverseOrder());
		
		//for (int i = 0; i < tri.length; i++) {
		//	System.out.println( tri[i]);
		//}//TODO a supprimer

		
		
		return tri;
	}

}
