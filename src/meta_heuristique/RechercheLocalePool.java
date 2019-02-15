package meta_heuristique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
		
		int scoreObtenu = score.calculScore(dataHeuristique);;
		
		List<Integer> serverOnCenter2 = heuristique.getServerOnCenter();

		List<List<Integer>> serverOnEachRow = heuristique.getServerOnEachRow();
		
		Random random = new Random();
		
		
		boolean scoreUpgrade = false;
		int iteration = 0;
		
		int bestScore = score.calculScore(dataHeuristique);
		

		do {
			int bestMoveServ2 = -1;
			int bestMovePool2 = -1;
			int bestMoveServ1 = -1;
			int bestMovePool1 = -1;
			iteration ++;
			List<Map<Integer, List<Integer>>> numServInPoolInRow = getNumServInPoolInRow(dataHeuristique, serverOnCenter2);

			
			int pool = score.getPool();
				
				System.out.println("Pool : " + pool);
				scoreUpgrade = false;
				for (int r = 0; r < dataHeuristique.getNbRow(); r++) {
					for (int numServ : numServInPoolInRow.get(pool).get(r)) {
						for (int r1 = 0; r1 < dataHeuristique.getNbRow(); r1++) {
							if (r != r1) {
								for (int numServ2 : serverOnEachRow.get(r1)) {
										int k = dataHeuristique.getServeurs(numServ2).getPoule();
										if (k != pool) {
														
											dataHeuristique.getServeurs(numServ).setPoule(k);
											dataHeuristique.getPoule(k).getServeurs().remove(dataHeuristique.getServeurs(numServ2));
											dataHeuristique.getPoule(k).addServeur(dataHeuristique.getServeurs(numServ));
											dataHeuristique.getServeurs(numServ2).setPoule(pool);
											dataHeuristique.getPoule(pool).getServeurs().remove(dataHeuristique.getServeurs(numServ));
											dataHeuristique.getPoule(pool).addServeur(dataHeuristique.getServeurs(numServ2));
											
											scoreObtenu = score.calculScore(dataHeuristique);
											if (bestScore < scoreObtenu) {
												bestMoveServ2 = numServ2;
												bestMovePool2 = k;
												bestMoveServ1 = numServ;
												bestMovePool1 = pool;
	
	
												if (bestScore != scoreObtenu){
													bestScore = score.calculScore(dataHeuristique);
												}
											}
	
											dataHeuristique.getServeurs(numServ).setPoule(pool);
											dataHeuristique.getPoule(pool).getServeurs().remove(dataHeuristique.getServeurs(numServ2));
											dataHeuristique.getPoule(pool).addServeur(dataHeuristique.getServeurs(numServ));
											dataHeuristique.getServeurs(numServ2).setPoule(k);
											dataHeuristique.getPoule(k).getServeurs().remove(dataHeuristique.getServeurs(numServ));
											dataHeuristique.getPoule(k).addServeur(dataHeuristique.getServeurs(numServ2));
	
											scoreObtenu = score.calculScore(dataHeuristique);
											numServInPoolInRow = getNumServInPoolInRow(dataHeuristique, serverOnCenter2);
										}
								}	
							}
						}

						
					}
				}
			
			
			if (bestMoveServ2 != -1) {
				dataHeuristique.getServeurs(bestMoveServ1).setPoule(bestMovePool2);
				dataHeuristique.getPoule(bestMovePool2).getServeurs().remove(dataHeuristique.getServeurs(bestMoveServ2));
				dataHeuristique.getPoule(bestMovePool2).addServeur(dataHeuristique.getServeurs(bestMoveServ1));
				dataHeuristique.getServeurs(bestMoveServ2).setPoule(bestMovePool1);
				dataHeuristique.getPoule(bestMovePool1).getServeurs().remove(dataHeuristique.getServeurs(bestMoveServ1));
				dataHeuristique.getPoule(bestMovePool1).addServeur(dataHeuristique.getServeurs(bestMoveServ2));
				scoreUpgrade = true;

				scoreObtenu = score.calculScore(dataHeuristique);
				System.out.println("Iteration n°"+ (iteration) + " " + scoreObtenu);
			}
		} while (scoreUpgrade);
			
		return dataHeuristique;
	}

}