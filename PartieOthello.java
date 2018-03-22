package othello;

public class PartieOthello extends Partie {
	
	public PartieOthello() {
		etat = new EtatOthello();
		j1 = new JoueurOthello(Pion.NOIR);
		j2 = new JoueurOthello(Pion.BLANC);
		joueurCourant = j1;
	}
	
	public boolean estTerminee() {
		boolean estGagnee = false;
		
		if (etat.encoreDesCasesVides()) {
			
		}
		
		return estGagnee;
	}
	
}
