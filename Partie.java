package othello;

import othello.eval.Eval0;

public abstract class Partie {

	protected Etat etat;
	
	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	protected Joueur gagnant;
	
	
	public Partie() {}
	
	public Partie(Joueur un, Joueur deux) {
		j1 = un;
		j2 = deux; 
	}
	
	protected abstract void allerSurUnSuccesseur();
	protected abstract void aucunSuccesseur(boolean aff);
	protected abstract boolean estTerminee();
	
	public Joueur lancer(int c, boolean aff, Eval0... eval0s) {
		joueurCourant = j1;
		
		while (!estTerminee()) {
			tour(c, aff, eval0s);
		}
		
		gagnant = getGagnant();
		
		return getGagnant();
	}
	
	protected abstract void tour(int c, boolean aff, Eval0... eval0s);	
	public abstract Joueur getGagnant();
	 
	public Joueur getJoueurCourant(){
		 return joueurCourant;
	 }

	public Joueur getJoueurSuivant() {
		if (joueurCourant == j1) {
			return j2;
		}
		else {
			return j1;
		}
	}

	public Joueur getJoueurSuivant(Joueur j) {
		if (j == j1) {
			return j2;
		}
		else if (j == j2) {
			return j1;
		}
		
		assert false;
		
		return null;
	}

	public boolean isGagnant(Joueur j) {
		return j == gagnant;
	}
	
}
