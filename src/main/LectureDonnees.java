package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Coord;
import model.Serveur;


public class LectureDonnees {
	
	public Data getFromFile(String name) {
		
		Data data = null;
		
		try {
			File f = new File(name);
			Scanner scanner = new Scanner(f);
			
			try {
				
				//Lecture de la premi�re ligne
				int nbRow = scanner.nextInt();
				int nbSlot = scanner.nextInt();
				int nbSlotIndispo = scanner.nextInt();
				int nbPoule = scanner.nextInt();
				int nbServeur = scanner.nextInt();
				
				data = new Data(nbRow, nbSlot, nbSlotIndispo, nbPoule, nbServeur);
				
				//Lecture des slots indisponibles
				for (int i = 0; i < data.getNbSlotIndispo(); i++) {
					data.setCoordSlotIndispo(i, new Coord(scanner.nextInt(),scanner.nextInt()));
				}
				
				//Lecture des cract�ristiques serveur ( taille et capacit�)
				for(int i=0; i < data.getNbServeur(); i++) {
					data.setServeurs(i, new Serveur(i, scanner.nextInt(),scanner.nextInt()));
				}
					
				
			}catch (NoSuchElementException exception) {
				System.out.println("erreur");
			}
			scanner.close();
		}
		catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas �t� trouv�");
		}
		
		return data;
	}
}
