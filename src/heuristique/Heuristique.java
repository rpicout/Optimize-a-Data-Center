package heuristique;

import java.util.ArrayList;
import java.util.List;

import main.Data;
import model.Serveur;

public class Heuristique {

	// On tri les serveur en capacit� d�croissante et taille croissante pour les
	// mettre dans les ranger pour avoir le plus de capacit� posible

	/**
	 * On trie le tableau les capacit�s des serveurs par ordre d�croissant
	 * 
	 * @param data
	 * @return le tableau d'indice du tableau avec les capacit�s tri�es par ordre
	 *         d�croissant
	 */
	public int[] getTriServeurCapaciteTaille(Data data) {

		Serveur[] serveurs = data.getServeurs();
		int[] triServeur = new int[data.getNbServeur()];
		int numTailleMax;
		int capaciteMax;

		for (int i = 0; i < data.getNbServeur(); i++) {
			triServeur[i] = i;
		}

		for (int i = data.getNbServeur() - 1; i >= 0; i--) {

			numTailleMax = 0;

			for (int j = 0; j <= i; j++) {
				if (serveurs[triServeur[j]].getCapacite() < serveurs[triServeur[numTailleMax]].getCapacite()) {
					numTailleMax = j;
				}
			}

			capaciteMax = triServeur[numTailleMax];
			triServeur[numTailleMax] = triServeur[i];
			triServeur[i] = capaciteMax;

		}

		// Maintenant que les c'est tri� par capacit� il faut trier par taille �
		// "l'int�rieur" d'une meme capacit�

		// TODO � terminer !!
		int currentCapacite = serveurs[triServeur[0]].getCapacite();
		List<Serveur> capaConstante = new ArrayList<Serveur>();
		int indiceTailleMax, tailleMax;
		boolean next = false;
		boolean finCalcul = false;

		for (int i = 0; i < data.getNbServeur(); i++) {

			finCalcul = false;

			while (!finCalcul) {
				next = false;
				while (!next) { // TODO probleme d'indice (outOfBounds) que je ne trouve pas --'
					if (serveurs[triServeur[i]].getCapacite() == currentCapacite) {
						capaConstante.add(data.getServeurs(triServeur[i]));
						i++;
					} else {
						currentCapacite = serveurs[triServeur[i]].getCapacite();
						next = true;
					}
				}

				for (int j = capaConstante.size() - 1; j >= 0; j--) {

					indiceTailleMax = 0;

					for (int k = 0; k <= j; k++) {
						if (capaConstante.get(k).getTaille() < capaConstante.get(indiceTailleMax).getTaille()) {
							indiceTailleMax = k;
						}
					}

					tailleMax = capaConstante.get(indiceTailleMax).getNumero();
					capaConstante.get(indiceTailleMax).setNumero(capaConstante.get(j).getNumero());
					capaConstante.get(j).setNumero(tailleMax);

				}
				int j = 0;
				for (int x = i; x < i - capaConstante.size(); x--) {
					triServeur[x] = capaConstante.get(capaConstante.size() - j).getNumero();
					j++;
				}
				capaConstante.clear();
				finCalcul = true;

			}

			// System.out.println("Numero de serveur : " + triServeur[i] + ", Capacit� : " +
			// serveurs[triServeur[i]].getCapacite() + ", Taille : " +
			// serveurs[triServeur[i]].getTaille());
		}
		for (int i = 0; i < data.getNbServeur(); i++) {
			System.out.println("Numero de serveur : " + triServeur[i] + ", Capacit� : "
					+ serveurs[triServeur[i]].getCapacite() + ", Taille : " + serveurs[triServeur[i]].getTaille());
		}
		return triServeur;
	}

}
