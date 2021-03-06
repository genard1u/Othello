package othello.partie;

import othello.etat.Etat;
import othello.eval.Eval0;
import othello.joueur.Joueur;

public abstract class Partie {

	protected Etat etat;
	
	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	
	
	protected Partie() {}
	
	protected Partie(Joueur un, Joueur deux) {
		j1 = un;
		j2 = deux; 
	}
	
	protected abstract void allerSurUnSuccesseur();
	protected abstract void aucunSuccesseur(boolean affichage);
	protected abstract void tour(int profondeur, boolean affichage, Eval0... eval0s);	
	protected abstract boolean estTerminee();
	protected abstract Joueur gagnant();
	
	public Joueur lancer(int c, boolean affichage, Eval0... eval0s) {
		assert joueurCourant == j1;
		
		while (!estTerminee()) {
			tour(c, affichage, eval0s);
		}
		
		return gagnant();
	}	
	
}
