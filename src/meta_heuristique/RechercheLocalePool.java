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
import model.Coord;
import model.Serveur;
import model.Slot;

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
		
		int currentScore = score.calculScore(dataHeuristique);
		int scoreObtenu;
		
		Random random = new Random();
		
		List<Integer> serverOnCenter2 = heuristique.getServerOnCenter();
		

		for (int iteration = 0; iteration < 10; iteration++) {
			List<Map<Integer, List<Integer>>> numServInPoolInRow = getNumServInPoolInRow(dataHeuristique, serverOnCenter2);
			for (int i = 0; i < dataHeuristique.getNbPoule(); i++) {
				System.out.println("Pool1 : " + i);
				for (int r = 0; r < dataHeuristique.getNbRow(); r++) {
					int numServ = -1;
					if (numServInPoolInRow.get(i).get(r).size() != 0) {
						int alea = random.nextInt(numServInPoolInRow.get(i).get(r).size());
						numServ = numServInPoolInRow.get(i).get(r).get(alea);
					}
					int k = 0;
					while (numServ != -1 && k < dataHeuristique.getNbPoule()) {
						if (i != k) {
							int r1 = 0;
							int numServ2 = -1;
							while (r1 < dataHeuristique.getNbRow() && numServ2 == -1) {
								if (numServInPoolInRow.get(k).get(r1).size() != 0) {
									int alea2 = random.nextInt(numServInPoolInRow.get(k).get(r1).size());
									numServ2 = numServInPoolInRow.get(k).get(r1).get(alea2);
								}
								r1++;
							}
							if (numServ2 != -1) {
								
								dataHeuristique.getServeurs(numServ).setPoule(k);
								dataHeuristique.getPoule(k).getServeurs().remove(dataHeuristique.getServeurs(numServ2));
								dataHeuristique.getPoule(k).addServeur(dataHeuristique.getServeurs(numServ));
								dataHeuristique.getServeurs(numServ2).setPoule(i);
								dataHeuristique.getPoule(i).getServeurs().remove(dataHeuristique.getServeurs(numServ));
								dataHeuristique.getPoule(i).addServeur(dataHeuristique.getServeurs(numServ2));
								
								scoreObtenu = score.calculScore(dataHeuristique);
								if (scoreObtenu <= currentScore) {
									dataHeuristique.getServeurs(numServ).setPoule(i);
									dataHeuristique.getPoule(i).getServeurs().remove(dataHeuristique.getServeurs(numServ2));
									dataHeuristique.getPoule(i).addServeur(dataHeuristique.getServeurs(numServ));
									dataHeuristique.getServeurs(numServ2).setPoule(k);
									dataHeuristique.getPoule(k).getServeurs().remove(dataHeuristique.getServeurs(numServ));
									dataHeuristique.getPoule(k).addServeur(dataHeuristique.getServeurs(numServ2));
								}
								else {
									numServ = -1;

									currentScore = score.calculScore(dataHeuristique);
									System.out.println("Iteration n°"+ (iteration+1) + " " + currentScore);
								}
								

								numServInPoolInRow = getNumServInPoolInRow(dataHeuristique, serverOnCenter2);
							}
						}
						k++;
					}
				}
			}
		}
			
		return dataHeuristique;
	}

}
