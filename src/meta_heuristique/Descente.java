package meta_heuristique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import main.Data;
import main.Score;
import model.Coord;
import model.Serveur;
import model.Slot;

/**
 * Ne marche pas !!
 * @author Romane
 *
 */
public class Descente {
	
	private Score score = new Score();
	
	public Data getSolutionDescente (Data data, Data currentSol) {
		
		double proba = Math.random();
		
		double p1 = 0.3;
		double p2 = 0.3;
		double p3 = 0.4;
		
		Random random = new Random(); 
		
		int numServeurAlea;
		int numSlotAlea;
		int numRowAlea;
		int numPoolAlea;
		
		int scoreMax = score.calculScore(data);
		
		for (int iteration = 0; iteration < 10000; iteration ++) {

			currentSol = data;

			System.out.println("Score itération n° " + (iteration + 1) + " : " + score.calculScore(currentSol));
			proba = Math.random();
			// On enlève un serveur au hasard
			if (proba <= p1) {
				System.out.println("p"+1);
				boolean enlever = false;
				while (!enlever) {
					numServeurAlea = random.nextInt(624);
					if (currentSol.getServeurs(numServeurAlea).getPoule() != -1) {
						int r = 0;
						int s = 0;
						Serveur serveur;
						
						while (!enlever && r < currentSol.getNbRow() && s < currentSol.getNbSlot()) {
							serveur = currentSol.getRow(r).getSlot(s).getServeur();
							
							if (serveur == currentSol.getServeurs(numServeurAlea)) {
								for (int k = s; k < serveur.getTaille() + s; k++) {
									if (k == 100)
										currentSol.getRow(r).getSlot(k).setServeur(null);
									else 
										currentSol.getRow(r).getSlot(k).setServeur(null);
										
								}
								currentSol.getPoule(currentSol.getServeurs(numServeurAlea).getPoule()).getServeurs().remove(currentSol.getServeurs(numServeurAlea));
								currentSol.getServeurs(numServeurAlea).setPoule(-1);
								
								if (scoreMax < score.calculScore(currentSol)) {
									scoreMax = score.calculScore(currentSol);
									data = currentSol;
									System.out.println("Score itération n° " + (iteration + 1) + " : " + scoreMax);
								}
								else 
									currentSol = null;
								
								enlever = true;
							}
							else {
								s++;
								if (s == currentSol.getNbSlot()) {
									s = 0;
									r++;
								}
							}
						}
					}
				}
			}
			else {
				//Placer un serveur qui n’est pas encore affecté sur une rangée et un slot libre aléatoire et sur une pool aléatoire
				if (proba <= p2 + p1) {
					System.out.println("p"+2);
	
					List<Coord> slotDispo = new ArrayList<Coord>();
					
					for (int j = 0; j < currentSol.getNbRow(); j++) {
						for (int k = 0; k < currentSol.getNbSlot(); k++) {
							if (!currentSol.getRow(j).getSlot(k).isIndispo() && currentSol.getRow(j).getSlot(k).getServeur() == null) {
							//	System.out.println("1");
								slotDispo.add(new Coord(j, k));
							}
						}
					}
					boolean ajouter = false;
					while (!ajouter && !slotDispo.isEmpty()) {
						
						int truc = random.nextInt(slotDispo.size());
						
						numRowAlea = slotDispo.get(truc).getX();
						numSlotAlea = slotDispo.get(truc).getY();
						
						slotDispo.remove(truc);
						
						int k = numSlotAlea;
						int taille = 0;
						Slot slot = currentSol.getRow(numRowAlea).getSlot(k);
						while (slot.getServeur() == null && !slot.isIndispo() && k < currentSol.getNbSlot()) {
						//	System.out.println("2");
							slot = currentSol.getRow(numRowAlea).getSlot(k);
							taille ++;
							k ++;
						}
						
						int i = 0;
						while (!ajouter &&  i < currentSol.getNbServeur()) {
							if (currentSol.getServeurs(i).getPoule() == -1 && currentSol.getServeurs(i).getTaille() < taille) {
								int tailleServeur = currentSol.getServeurs(i).getTaille();
								
								for (int j = numSlotAlea; j < tailleServeur + numSlotAlea; j++) {
								//	System.out.println("4");
									currentSol.getRow(numRowAlea).getSlot(j).setServeur(currentSol.getServeurs(i));
								}
								
								numPoolAlea = random.nextInt(44);
								
								currentSol.getServeurs(i).setPoule(numPoolAlea);
								currentSol.getPoule(numPoolAlea).addServeur(currentSol.getServeurs(i));
								
								if (scoreMax < score.calculScore(currentSol)) {
									scoreMax = score.calculScore(currentSol);
									data = currentSol;
									System.out.println("Score itération n° " + (iteration + 1) + " : " + scoreMax);
								}
								else 
									currentSol = null;
								
								ajouter = true;
							}
							else
								i++;
							//System.out.println("3 : "+(i-1) + " " + ajouter);
							
						}
					}
				}
				//Affecter un serveur qui est déjà placé à un pool aléatoire
				else {
					System.out.println("p"+3);
					boolean changer = false;
					while (!changer) {
						numServeurAlea = random.nextInt(624);
						if (currentSol.getServeurs(numServeurAlea).getPoule() != -1) {
							List<Integer> poolAlea = new ArrayList<Integer>();
							for (int i = 0; i < currentSol.getNbPoule(); i++)
								poolAlea.add(i);
							
							do {
								int alea = random.nextInt(poolAlea.size());
								numPoolAlea = poolAlea.get(alea);
								poolAlea.remove(alea);
							} while (numPoolAlea != currentSol.getServeurs(numServeurAlea).getPoule() && !poolAlea.isEmpty());
							
							currentSol.getPoule(currentSol.getServeurs(numServeurAlea).getPoule()).getServeurs().remove(currentSol.getServeurs(numServeurAlea));
							currentSol.getServeurs(numServeurAlea).setPoule(numPoolAlea);
							currentSol.getPoule(numPoolAlea).addServeur(currentSol.getServeurs(numServeurAlea));
							
							if (scoreMax < score.calculScore(currentSol)) {
								scoreMax = score.calculScore(currentSol);
								data = currentSol;
								System.out.println("Score itération n° " + (iteration + 1) + " : " + scoreMax);
							}
							else 
								currentSol = null;
							
							changer = true;
						}
					}
				}
			}
		}
		return data;
	}

}
