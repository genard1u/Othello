package othello.partie;

import othello.etat.Etat;
import othello.eval.Eval0;
import othello.joueur.Joueur;

public abstract class Partie {

	protected Etat etat;
	
	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	protected Joueur gagnant;
	
	
	protected Partie() {}
	
	protected Partie(Joueur un, Joueur deux) {
		j1 = un;
		j2 = deux; 
	}
	
	protected abstract void allerSurUnSuccesseur();
	protected abstract void aucunSuccesseur(boolean aff);
	protected abstract boolean estTerminee();
	
	public Joueur lancer(int c, boolean affichage, Eval0 ... eval0s) {
		joueurCourant = j1;
		
		while (!estTerminee()) {
			tour(c, affichage, eval0s);
		}
		
		gagnant = getGagnant();
		
		return getGagnant();
	}
	
	protected abstract void tour(int c, boolean affichage, Eval0 ... eval0s);	
	public abstract Joueur getGagnant();

	public boolean estGagnant(Joueur j) {
		return j == gagnant;
	}
	
}
