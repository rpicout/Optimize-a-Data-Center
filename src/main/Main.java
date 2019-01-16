package main;

import heuristique.BinPacking;

public class Main {

	public static void main(String[] args) {
		
		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data = f.getFromFile(fichierData);
		
	}

}
