package main;

import heuristique.RowEquivalente;
import meta_heuristique.RechercheLocalePool;

public class Main {

	public static void main(String[] args) {

		EcritureResultats ecritureResultats = new EcritureResultats();
		Score score = new Score();

		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data = f.getFromFile(fichierData);
		
		RowEquivalente heuristique = new RowEquivalente();
		Data dataHeuristique = heuristique.getSolution(data);
		ecritureResultats.write(dataHeuristique, "RowEquivalent");
		System.out.println("Score : " + score.calculScore(dataHeuristique));		
		
		RechercheLocalePool rechercheLocalePool = new RechercheLocalePool();
		Data dataRLocaleP = rechercheLocalePool.getSolutionRechercheLocalePool(dataHeuristique, heuristique);
		ecritureResultats.write(dataRLocaleP, "RechercheLocalePool");
		System.out.println("Score : " + score.calculScore(dataRLocaleP));		

	}

}
