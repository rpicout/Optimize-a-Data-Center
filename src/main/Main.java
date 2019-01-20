package main;

import heuristique.BinPacking;
import heuristique.Gloutonne;

public class Main {

	public static void main(String[] args) {
		
		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data = f.getFromFile(fichierData);

		EcritureResultats ecritureResultats = new EcritureResultats();
		Score score = new Score();

		Gloutonne gloutonne = new Gloutonne();
		Data newdata = gloutonne.getSolution(data);
		ecritureResultats.write(newdata, "Gloutonne");
		System.out.println("Score : " + score.calculScore(newdata));
		
		
		BinPacking binPacking = new BinPacking();
		Data essai = binPacking.getSolution(data);
		ecritureResultats.write(essai, "BinPacking");
		System.out.println("Score : " + score.calculScore(essai));

	}

}
