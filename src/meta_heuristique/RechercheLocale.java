package meta_heuristique;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Data;
import main.Score;
import model.Coord;
import model.Serveur;
import model.Slot;

public class RechercheLocale {

	
	private Score score = new Score();
	private Data data;
	
	public Data getSolutionRechercheLocale(Data currentData) {
		System.out.println("Début recherche locale");
		
		int currentScore = score.calculScore(currentData);
		int scoreObtenu;
		
		Random random = new Random();
		
		data = currentData;
		
		for (int iteration = 0; iteration < 50; iteration++) {
			boolean modifier = false;
			int numServeurAlea1;
			int numServeurAlea2;
			
			while(!modifier) {
				numServeurAlea1 = random.nextInt(624);
				numServeurAlea2 = random.nextInt(624);
				
				Coord coordServ1 = null;
				Coord coordServ2 = null;
				
				if (data.getServeurs(numServeurAlea1).getPoule() != -1 && data.getServeurs(numServeurAlea2).getPoule() != -1) {
					
					boolean findServ1 = false;
					boolean findServ2 = false;
					boolean bothFind = false;
					int r = 0;
					int s = 0;
					while (!bothFind && r < data.getNbRow() && s < data.getNbSlot()) {
	
						Slot slot = data.getRow(r).getSlot(s);
						Serveur serveur = null;
						
						if (slot.getServeur() == data.getServeurs(numServeurAlea1)) {
							serveur = data.getServeurs(numServeurAlea1);
							for (int i = s; i < s + serveur.getTaille(); i++) {
								slot = data.getRow(r).getSlot(i);
								slot.setServeur(null);
							}
							coordServ1 = new Coord(r, s);
							
							findServ1 = true;
						} else if (slot.getServeur() == data.getServeurs(numServeurAlea2)) {
							serveur = data.getServeurs(numServeurAlea2);
							for (int i = s; i < s + serveur.getTaille(); i++) {
								slot = data.getRow(r).getSlot(i);
								slot.setServeur(null);
							}
							coordServ2 = new Coord(r, s);
							
							findServ2 = true;
						} else {
							s++;
							if (s == data.getNbSlot()) {
								s = 0;
								r++;
							}
						}
						if (findServ1 && findServ2)
							bothFind = true;
							
					}
					
					if (findServ1 && findServ2) {
						if (data.getServeurs(numServeurAlea1).getTaille() == data.getServeurs(numServeurAlea2).getTaille()) {
							for (int i = coordServ1.getY(); i < coordServ1.getY() + data.getServeurs(numServeurAlea2).getTaille(); i++) {
								data.getRow(coordServ1.getX()).getSlot(i).setServeur(data.getServeurs(numServeurAlea2));
							}
							for (int i = coordServ2.getY(); i < coordServ2.getY() + data.getServeurs(numServeurAlea1).getTaille(); i++) {
								data.getRow(coordServ2.getX()).getSlot(i).setServeur(data.getServeurs(numServeurAlea1));
							}
						} else {
							
							List<Serveur> serveurDispo = new ArrayList<Serveur>();
							
							if (data.getServeurs(numServeurAlea1).getTaille() < data.getServeurs(numServeurAlea2).getTaille()) {
								for (int i = coordServ2.getY(); i < coordServ2.getY() + data.getServeurs(numServeurAlea1).getTaille(); i++) {
									data.getRow(coordServ2.getX()).getSlot(i).setServeur(data.getServeurs(numServeurAlea1));
								}
								
								data.getPoule(data.getServeurs(numServeurAlea2).getPoule()).getServeurs().remove(data.getServeurs(numServeurAlea2));
								data.getServeurs(numServeurAlea2).setPoule(-1);
								
								for (int j = 0; j < data.getNbServeur(); j++) {
									if (data.getServeurs(j).getPoule() == -1) {
										serveurDispo.add(data.getServeurs(j));
									}
								}
								
								int tailleserv = 0;
								for (Serveur serveur : serveurDispo) {
									if (tailleserv + serveur.getTaille() <= data.getServeurs(numServeurAlea2).getTaille()) {
										for (int i = coordServ2.getY() + tailleserv; i < coordServ2.getY() + tailleserv + serveur.getTaille(); i++) {
											data.getRow(coordServ2.getX()).getSlot(i).setServeur(serveur);
										}
										
										int p = Integer.MAX_VALUE;   
										int poolRetenu = 0;
										for(int j = 0 ; j < data.getNbPoule(); j++){
											if(data.getPoule(j).getServeurs().size() + 1 < p) {
												p = data.getPoule(j).getServeurs().size();
												poolRetenu = j;
											}
										}
										//On ajoute au pool retenu
										serveur.setPoule(poolRetenu);
										data.getPoule(poolRetenu).addServeur(serveur);
										
										tailleserv += serveur.getTaille();
									}
								}
								
							} else {
								for (int i = coordServ1.getY(); i < coordServ1.getY() + data.getServeurs(numServeurAlea2).getTaille(); i++) {
									data.getRow(coordServ1.getX()).getSlot(i).setServeur(data.getServeurs(numServeurAlea2));
								}

								data.getPoule(data.getServeurs(numServeurAlea1).getPoule()).getServeurs().remove(data.getServeurs(numServeurAlea1));
								data.getServeurs(numServeurAlea1).setPoule(-1);
								
								for (int j = 0; j < data.getNbServeur(); j++) {
									if (data.getServeurs(j).getPoule() == -1) {
										serveurDispo.add(data.getServeurs(j));
									}
								}
								
								int tailleserv = 0;
								for (Serveur serveur : serveurDispo) {
									if (tailleserv + serveur.getTaille() <= data.getServeurs(numServeurAlea2).getTaille()) {
										for (int i = coordServ1.getY() + tailleserv; i < coordServ1.getY() + tailleserv + serveur.getTaille(); i++) {
											data.getRow(coordServ1.getX()).getSlot(i).setServeur(serveur);
										}
										
										int p = Integer.MAX_VALUE;   
										int poolRetenu = 0;
										for(int j = 0 ; j < data.getNbPoule(); j++){
											if(data.getPoule(j).getServeurs().size() + 1 < p) {
												p = data.getPoule(j).getServeurs().size();
												poolRetenu = j;
											}
										}
										//On ajoute au pool retenu
										serveur.setPoule(poolRetenu);
										data.getPoule(poolRetenu).addServeur(serveur);
										
										tailleserv += serveur.getTaille();
									}
								}
							}
							
							
						}
						
						modifier = true;
					} 
					
					
				}
			}

			scoreObtenu = score.calculScore(data);
			if (scoreObtenu > currentScore)
				currentData = data;
			else 
				data = currentData;
			scoreObtenu = score.calculScore(data);
		}
		System.out.println("Fin recherche locale");
		return data;
	}
}
