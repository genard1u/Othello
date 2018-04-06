package othello;

import othello.eval.Eval0;

public abstract class Partie {

	protected static Etat etat;
	
	protected static Joueur j1;
	protected static Joueur j2;
	
	protected static Joueur joueurCourant;
	protected static Eval0 eval0;
	
	
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
	public abstract Joueur getGagnant();
	 public static Joueur getJoueurCourant(){
		 return joueurCourant;
	 }

	public static Joueur getJoueurSuivant() {
		if (joueurCourant == j1) {
			return j2;
		}
		else {
			return j1;
		}
	}

	public static Eval0 getEval0() {
		return eval0;
	}

	public static Joueur getJoueurSuivant(Joueur j) {
		if (j == j1) {
			return j2;
		}
		else {
			return j1;
		}
	}
	
}
