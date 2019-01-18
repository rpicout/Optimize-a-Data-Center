package main;

import heuristique.BinPacking;
import heuristique.Gloutonne;

public class Main {

	public static void main(String[] args) {
		
		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data = f.getFromFile(fichierData);
		

		Gloutonne gloutonne = new Gloutonne();

		int[] triServeurCapacite = gloutonne.getTriServeurCapacite(data);
		
		BinPacking binPacking = new BinPacking();
		int[] triServeurTaille = binPacking.getTriServeurTaille(data);

		Data newdata = gloutonne.getSolution(data);
		
		EcritureResultats ecritureResultats = new EcritureResultats();
		ecritureResultats.write(newdata);

	}

}
