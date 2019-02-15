package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Serveur;


public class LectureDonnees {
	
	public Data getFromFile(String name) {
		
		Data data = null;
		
		try {
			File f = new File(name);
			Scanner scanner = new Scanner(f);
			
			try {
				System.out.println("Lecture des données");
				//Lecture de la première ligne
				int nbRow = scanner.nextInt();
				int nbSlot = scanner.nextInt();
				int nbSlotIndispo = scanner.nextInt();
				int nbPoule = scanner.nextInt();
				int nbServeur = scanner.nextInt();
				
				data = new Data(nbRow, nbSlot, nbSlotIndispo, nbPoule, nbServeur);
				
				int row;
				int slot;
				
				//Lecture des slots indisponibles
				for (int i = 0; i < data.getNbSlotIndispo(); i++) {
					row = scanner.nextInt();
					slot = scanner.nextInt();
					data.getRow(row).getSlot(slot).setIndispo(true);
				}
				
				//Lecture des cractéristiques serveur ( taille et capacité)
				for(int i=0; i < data.getNbServeur(); i++) {
					data.setServeurs(i, new Serveur(i, scanner.nextInt(),scanner.nextInt()));
				}
					
				System.out.println("Fin lecture des données");
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
