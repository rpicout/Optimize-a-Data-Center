package meta_heuristique;

import main.Data;
import main.Score;

public class RechercheLocale {

	
	/**
	 * CurrentSol = solution al�atoire satisfaisant les contraintes du programme lin�aire 
		Tant que nous ne trouvons pas de solution optimale Faire 
/			CurrentCapGarantie = r�sultat de la fonction objective obtenu avec CurrentSol 
			Prendre un serveur i al�atoirement
			Pour j allant de 1 � nbRang�es
				Enlever ce serveur du slot, de la rang�e et de la pool auxquels il se trouve
				Ins�rer le serveur i dans le slot s, la rang�e r et la pool p en respectant les contraintes
				Sol = solution obtenue en ayant ins�rer i dans s, r et p 
				TpsCharg = r�sultat de la fonction objective obtenu avec Sol
				Si CurrentCapGarantie > capGarantie Alors
					CurrentCapGarantie = capGarantie
					CurrentSol = Sol
				Sinon
					On garde inchang� CurrentSol et CurrentCapGarantie
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
