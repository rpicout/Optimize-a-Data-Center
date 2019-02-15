package main;

import heuristique.BinPacking;
import heuristique.Gloutonne;
import heuristique.Heuristique;
import heuristique.Heuristique2;
import meta_heuristique.Descente;
import meta_heuristique.RechercheLocalePool;

public class Main {

	public static void main(String[] args) {
		
		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data = f.getFromFile(fichierData);

		EcritureResultats ecritureResultats = new EcritureResultats();
		Score score = new Score();
		
		RechercheLocalePool rechercheLocalePool = new RechercheLocalePool();
		data = f.getFromFile(fichierData);
		Data dataRLocaleP = rechercheLocalePool.getSolutionRechercheLocalePool();
		ecritureResultats.write(dataRLocaleP, "RechercheLocalePool");
		System.out.println("Score : " + score.calculScore(dataRLocaleP));

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
		

	}

}
