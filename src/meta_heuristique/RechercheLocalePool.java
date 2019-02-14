package meta_heuristique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import heuristique.Heuristique2;
import main.Data;
import main.LectureDonnees;
import main.Score;
import model.Serveur;

public class RechercheLocalePool {

	private Score score = new Score();
	
	public List<Map<Integer, List<Integer>>> getNumServInPoolInRow(Data dataHeuristique, List<Integer> serverOnCenter2) {

		List<Map<Integer, List<Integer>>> numServInPoolInRow = new ArrayList<>();
		for (int p = 0; p < dataHeuristique.getNbPoule(); p++) {
			Map<Integer, List<Integer>> map = new HashMap<>();
			numServInPoolInRow.add(map);
			for (int r = 0; r < dataHeuristique.getNbRow(); r++) {
				List<Integer> numServs = new ArrayList<Integer>();
				for (int numServ : serverOnCenter2) {
					Serveur serveur = dataHeuristique.getServeurs(numServ);
					if (serveur.getPoule() == p) {
						boolean capaciteAdd = false;
						int s = 0;
						while (!capaciteAdd && s < dataHeuristique.getNbSlot()) {
							if (dataHeuristique.getRow(r).getSlot(s).getServeur() == serveur) {
								capaciteAdd = true;
								numServs.add(numServ);
							}
							s++;
						}
					}
				}
				numServInPoolInRow.get(p).put(r, numServs);
			}
		}
		
		return numServInPoolInRow;
	}
	
	public Data getSolutionRechercheLocalePool () {

		String fichierData = "InputData/data-center_";

		LectureDonnees f = new LectureDonnees();
		Data data2 = f.getFromFile(fichierData);
		
		Heuristique2 heuristique = new Heuristique2();
		Data dataHeuristique = heuristique.getSolution(data2);
		
		List<List<Integer>> serverOnEachRow = heuristique.getServerOnEachRow();
		
		int currentScore = score.calculScore(dataHeuristique);
		int scoreObtenu;
		
		List<Integer> serverOnCenter2 = heuristique.getServerOnCenter();
		
		boolean scoreUpgrade = false;
		int iteration = 0;
		do {
			iteration ++;
			List<Map<Integer, List<Integer>>> numServInPoolInRow = getNumServInPoolInRow(dataHeuristique, serverOnCenter2);
			for (int i = 0; i < dataHeuristique.getNbPoule(); i++) {
				System.out.println("Pool : " + i);
				for (int r = 0; r < dataHeuristique.getNbRow(); r++) {
					for (int numServ : numServInPoolInRow.get(i).get(r)) {
						int bestMoveServ2 = -1;
						int bestMovePool2 = -1;
						int bestScore = score.calculScore(dataHeuristique);
						int r1 = 0;
						while (r1 < dataHeuristique.getNbRow()) {
							for (int numServ2 : serverOnEachRow.get(r1)) {
								int k = dataHeuristique.getServeurs(numServ2).getPoule();
								if (k != i) {
									dataHeuristique.getServeurs(numServ).setPoule(k);
									dataHeuristique.getPoule(k).getServeurs().remove(dataHeuristique.getServeurs(numServ2));
									dataHeuristique.getPoule(k).addServeur(dataHeuristique.getServeurs(numServ));
									dataHeuristique.getServeurs(numServ2).setPoule(i);
									dataHeuristique.getPoule(i).getServeurs().remove(dataHeuristique.getServeurs(numServ));
									dataHeuristique.getPoule(i).addServeur(dataHeuristique.getServeurs(numServ2));

									scoreObtenu = score.calculScore(dataHeuristique);
									if (bestScore <= scoreObtenu) {
										scoreUpgrade = true;
										
										bestScore = score.calculScore(dataHeuristique);
										bestMoveServ2 = numServ2;
										bestMovePool2 = k;
									}
									dataHeuristique.getServeurs(numServ).setPoule(i);
									dataHeuristique.getPoule(i).getServeurs().remove(dataHeuristique.getServeurs(numServ2));
									dataHeuristique.getPoule(i).addServeur(dataHeuristique.getServeurs(numServ));
									dataHeuristique.getServeurs(numServ2).setPoule(k);
									dataHeuristique.getPoule(k).getServeurs().remove(dataHeuristique.getServeurs(numServ));
									dataHeuristique.getPoule(k).addServeur(dataHeuristique.getServeurs(numServ2));
									scoreObtenu = score.calculScore(dataHeuristique);
								}
							}
							r1++;
						}
						if (bestMoveServ2 != -1) {
							dataHeuristique.getServeurs(numServ).setPoule(bestMovePool2);
							dataHeuristique.getPoule(bestMovePool2).getServeurs().remove(dataHeuristique.getServeurs(bestMoveServ2));
							dataHeuristique.getPoule(bestMovePool2).addServeur(dataHeuristique.getServeurs(numServ));
							dataHeuristique.getServeurs(bestMoveServ2).setPoule(i);
							dataHeuristique.getPoule(i).getServeurs().remove(dataHeuristique.getServeurs(numServ));
							dataHeuristique.getPoule(i).addServeur(dataHeuristique.getServeurs(bestMoveServ2));
							
							currentScore = score.calculScore(dataHeuristique);
							System.out.println("Iteration n°"+ (iteration) + " " + currentScore);
							numServInPoolInRow = getNumServInPoolInRow(dataHeuristique, serverOnCenter2);
						}
					}
				}
				
			}
		} while (scoreUpgrade);
			
		return dataHeuristique;
	}
	
}
