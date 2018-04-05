package othello;

import othello.eval.Eval0;

public abstract class Partie {

	protected Etat etat;
	
	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	protected Eval0 eval0;
	
	
	public Partie() {}
	
	public Partie(Joueur un, Joueur deux) {
		j1 = un;
		j2 = deux; 
	}
	
	protected abstract void allerSurUnSuccesseur();
	protected abstract void aucunSuccesseur();
	protected abstract boolean estTerminee();
	
	public Joueur lancer(Eval0... eval0s) {
		while (!estTerminee()) {
			tour(eval0s);
		}
		
		return getGagnant();
	}
	
	protected abstract void tour(Eval0... eval0s);
	protected abstract Joueur getGagnant();
	
}
