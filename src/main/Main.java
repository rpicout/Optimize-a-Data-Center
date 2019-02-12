package main;

import heuristique.BinPacking;
import heuristique.Gloutonne;
import heuristique.Heuristique;
import meta_heuristique.Descente;

public class Main {

	public static void main(String[] args) {
		
		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data = f.getFromFile(fichierData);

		EcritureResultats ecritureResultats = new EcritureResultats();
		Score score = new Score();

	/**	Gloutonne gloutonne = new Gloutonne();
		Data newdata = gloutonne.getSolution(data);
		ecritureResultats.write(newdata, "Gloutonne");
		System.out.println("Score : " + score.calculScore(newdata));
		
		data = f.getFromFile(fichierData);
		BinPacking binPacking = new BinPacking();

		Data dataBinPacking = binPacking.getSolution(data);
		ecritureResultats.write(dataBinPacking, "BinPacking");
		System.out.println("Score : " + score.calculScore(dataBinPacking));
		
		Descente descente = new Descente();
		data = f.getFromFile(fichierData);
		Data currentSol = binPacking.getSolution(data);
		Data dataDescente = descente.getSolutionDescente(dataBinPacking, currentSol);

		Data essai = binPacking.getSolution(data);
		ecritureResultats.write(essai, "BinPacking");
		System.out.println("Score : " + score.calculScore(essai));*/
		
		data = f.getFromFile(fichierData);
		Heuristique autreEssai = new Heuristique();
		int[] voir = autreEssai.getTriServeurCapaciteTaille(data);
		

	}

}
