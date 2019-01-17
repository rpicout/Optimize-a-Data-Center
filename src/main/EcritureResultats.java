package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureResultats {
	
	private File file;
	
	public void write (Data data, String name) {
		file = new File("OutputData/result."+ name);
		try {
			FileWriter fw = new FileWriter(file);
		    for (int i = 0; i < data.getNbServeur(); i++) {
		    	boolean find = false;
			    while (!find){
			    	for (int j = 0; j < data.getNbRow(); j++){
			    		for (int k = 0; k < data.getNbSlot(); k++){
			    			if (data.getRow(j).getSlot(k).getServeur().getNumero() == i){
			    				fw.write("" + j + " " + k);
			    				find = true;
			    			}
			    		}
			    	}
			    }
			    if (find){
				    find = false;
				    while (!find){
				    	for (int j = 0; j < data.getNbPoule(); j++){
				    		if (data.getPoule(j).getSlots(i).getNumero() == 1){
				    			fw.write(" " + j);
				    			find = true;
				    		}
				    	}
				    }
			    }
			    else {
			    	fw.write("x");
			    }
		    	fw.write("\n");
		    }
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
