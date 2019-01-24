package meta_heuristique;

import main.Data;
import main.Score;

public class RechercheLocale {

	
	/**
	 * CurrentSol = solution aléatoire satisfaisant les contraintes du programme linéaire 
		Tant que nous ne trouvons pas de solution optimale Faire 
/			CurrentCapGarantie = résultat de la fonction objective obtenu avec CurrentSol 
			Prendre un serveur i aléatoirement
			Pour j allant de 1 à nbRangées
				Enlever ce serveur du slot, de la rangée et de la pool auxquels il se trouve
				Insérer le serveur i dans le slot s, la rangée r et la pool p en respectant les contraintes
				Sol = solution obtenue en ayant insérer i dans s, r et p 
				TpsCharg = résultat de la fonction objective obtenu avec Sol
				Si CurrentCapGarantie > capGarantie Alors
					CurrentCapGarantie = capGarantie
					CurrentSol = Sol
				Sinon
					On garde inchangé CurrentSol et CurrentCapGarantie
				Fin Si
			Fin Pour
		Fin Tant que
	 */
	
	private Score score = new Score();
	
	public Data getSolutionRechercheLocale(Data data) {
		
		int currentScore = score.calculScore(data);
		
		return data;
	}
}
