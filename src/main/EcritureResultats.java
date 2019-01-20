package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Serveur;

public class EcritureResultats {
	
	private File file;
	
	public void write (Data data, String name) {
		System.out.println("Début écriture des résultats");
		file = new File("OutputData/result." + name);
		try {
			FileWriter fw = new FileWriter(file);
			
		    for (int i = 0; i < data.getNbServeur(); i++) {
		    	
		    	boolean find = false;
		    	int r = 0;
		    	int s = 0;
		    	
		    	while (!find && r < data.getNbRow() && s < data.getNbSlot()){
		    		
	    			Serveur serveur = data.getRow(r).getSlot(s).getServeur();
	    			
	    			if (serveur != null && serveur.getNumero() == i){
	    				fw.write(r + " " + s + " " + serveur.getPoule() + "\n");
	    				find = true;
	    			}
	    			
	    			if (!find) {
						s++;
						if (s == data.getNbSlot()) {
							s = 0;
							r ++;
						}
					}	
	    			
			    }
			    if (!find){
				    fw.write("x \n");
			    }
		    }
			fw.close();
			System.out.println("Fin écriture des résultats");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
