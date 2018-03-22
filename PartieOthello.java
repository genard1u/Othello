package othello;

public class PartieOthello extends Partie {
	
	private int passeSonTour;
	
	
	public PartieOthello(Joueur un, Joueur deux) {
		super(un, deux);
		((JoueurOthello) j1).setPion(Pion.NOIR);
		((JoueurOthello) j2).setPion(Pion.BLANC);
		joueurCourant = j1;
	}
	
	public boolean joueursBloques() {
		return passeSonTour == 2;
	}
	
	public boolean estTerminee() {
		boolean estGagnee = false;
		
		if (joueursBloques()) {
			estGagnee = true;
		}
		
		return estGagnee;
	}
	
}
