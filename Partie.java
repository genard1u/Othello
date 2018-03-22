package othello;

public abstract class Partie {

	protected Etat etat;
	
	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	
	protected abstract boolean estTerminee();
	protected Joueur jeu(){
		while (!estTerminee()){
			tour();
		}
		return getGagnant();
	}
	protected abstract void tour();
	protected abstract Joueur getGagnant();
}
