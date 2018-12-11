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
				
				//Lecture de la première ligne
				int nbRow = scanner.nextInt();
				int nbSlot = scanner.nextInt();
				int nbSlotIndispo = scanner.nextInt();
				int nbPoule = scanner.nextInt();
				int nbServeur = scanner.nextInt();
				
				data = new Data(nbRow, nbSlot, nbSlotIndispo, nbPoule, nbServeur);
				System.out.println(data.getNbRow()+", "+data.getNbSlot()+", "+data.getNbSlotIndispo()+", "+data.getNbPoule()+", "+data.getNbServeur());  // TODO A supprimer avant de rendre
				
				//Lecture des slots indisponibles
				for (int i = 0; i < data.getNbSlotIndispo(); i++) {
					data.setCoordSlotIndispo(i, new Coord(scanner.nextInt(),scanner.nextInt()));
					System.out.print(data.getCoordSlotIndispo(i) + " "); // TODO A supprimer avant de rendre
				}
				
				//Lecture des cractéristiques serveur ( taille et capacité)
				for(int i=0; i<data.getNbServeur();i++) {
					data.setServeurs(i, new Serveur(scanner.nextInt(),scanner.nextInt()));
					System.out.print(data.getServeurs(i) + " "); // TODO A supprimer avant de rendre
				}
					
				
			}catch (NoSuchElementException exception) {
				System.out.println("erreur");
			}
			scanner.close();
		}
		catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas été trouvé");
		}
		
		
		
		return data;
	}
}
