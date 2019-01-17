package main;

import heuristique.Gloutonne;

public class Main {

	public static void main(String[] args) {
		
		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data = f.getFromFile(fichierData);
		

		Gloutonne gloutonne = new Gloutonne();
		int[] triServeur = gloutonne.getTriServeurCapacite(data);
	}

}
