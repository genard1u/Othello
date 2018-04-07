package othello.etat;

import java.util.ArrayList;

import othello.Pion;
import othello.eval.Eval0;
import othello.joueur.Joueur;
import othello.joueur.JoueurOthello;
import othello.partie.Partie;

/**
 * @author Collignon Valentin
 * @author Genard Pierre
 */
public abstract class Etat {

	protected Eval0 eval0;
	
	
	protected Etat() {}
	
	protected Etat(Eval0 e) {
		eval0 = e;
	}
	
	public boolean equals(Etat e){
		return toString().equals(e.toString());
	}
	
	public boolean estFinal(Joueur j) {
		ArrayList<Etat> S = successeurs(j);
		boolean estFinal = false;
		
		if (S.size() == 0) {
			estFinal = true;
		}
		
		return estFinal;
	}
	
	public Etat minimax(Joueur courant, Joueur j1, Joueur j2, int c) {
		ArrayList<Etat> S = successeurs(courant);
		Etat eSortie = null;
		float score_max = Float.MIN_VALUE;
		float score_min = Float.MAX_VALUE;
		float score = 0;
		
		for (Etat e : S) {
			score = evaluation(courant, j1, j2, c);
			
			if (courant.estMachine()) {
				if (score >= score_max) {
					eSortie = e;
					score_max = score ;
				}
			}
			else {
				if (score <= score_min) {
					eSortie = e;
					score_min = score;
				}
			}
		}
			
		return eSortie;
	}
	
	public float evaluation(Joueur courant, Joueur j1, Joueur j2, int c) {
		Joueur jSuivant = Partie.getJoueurSuivant(j);
		ArrayList<Etat> S;
		float score = 0;
		float score_max = 0;
		float score_min = 0;
		
		if (estFinal(courant)) {
			
		}
		
		if (c == 0) {
			return Partie.getEval0().eval(this);
		}
		
		S = successeurs(j);
		
		if (((JoueurOthello)j).couleur() == Pion.NOIR) {
			score_max = Float.MIN_VALUE;
			
			for (Etat s : S) {
				score_max = Math.max(score_max, s.evaluation(c-1,jSuivant));
			}
			
			return score_max;
		}
		else {
			score_min = Float.MAX_VALUE;
			
			for (Etat s : S) {
				score_max = Math.min(score_max, s.evaluation(c-1,jSuivant));
			}
			
			return score_min;
		}
	}
	
	public Etat minimax_alpha_beta(Joueur courant, Joueur j1, Joueur j2, int c) {
		ArrayList<Etat> S = successeurs(courant);
		Etat eSortie = null;
		float score_min = Float.MAX_VALUE;
		float score_max = Float.MIN_VALUE;
		float score = 0;
		
		for (Etat e : S) {
			score = evaluation_alpha_beta(courant, j1, j2, c, Float.MIN_VALUE, Float.MAX_VALUE);
			
			if (courant.estMachine()) {
				if (score >= score_max) {
					eSortie = e;
					score_max = score ;
				}
			}
			else {
				if (score <= score_min) {
					eSortie = e;
					score_min = score;
				}
			}
		}			
		
		return eSortie;
	}
	
	private float evaluation_alpha_beta(int c, Joueur j, float alpha,float beta) 
	{
		Joueur jSuivant = Partie.getJoueurSuivant(j);
		ArrayList<Etat> S;
		float score = 0;
		float score_max = 0;
		float score_min = 0;
		
		if (estFinal(j)) {
			if ( ((JoueurOthello)j).couleur() == Pion.NOIR ){
				if (Partie.isGagnant(j)){
					return Float.MAX_VALUE;
				}else{
					if (Partie.isGagnant(Partie.getJoueurSuivant(j))){
						return Float.MIN_VALUE;
					}
				}
			}else{
				if (Partie.isGagnant(j)){
					return Float.MIN_VALUE;
				}else{
					if (Partie.isGagnant(Partie.getJoueurSuivant(j))){
						return Float.MAX_VALUE;
					}
				}
			
			}
			return 0;
			
		}
		if (c == 0) {
			return Partie.getEval0().eval(this);
		}
		S = successeurs(j);
		if (((JoueurOthello)j).couleur() == Pion.NOIR) {
			score_max = Float.MIN_VALUE;
			
			for (Etat s : S) {
				score_max = Math.max(score_max, s.evaluation_alpha_beta(c-1,jSuivant,alpha,beta));
				if (score_max>=beta){
					return score_max;
				}
				alpha = Math.max(alpha, score_max);
			}
			
			return score_max;
		}
		else {
			score_min = Float.MAX_VALUE;
			
			for (Etat s : S) {
				score_max = Math.min(score_max, s.evaluation_alpha_beta(c-1, jSuivant, alpha, beta));
				if(score_min<= alpha){
					return score_min;
				}
				beta = Math.min(beta, score_min);
			}
			
			return score_min;
		}
	}

	public abstract ArrayList<Etat> successeurs(Joueur j);

	public abstract int eval01();
	public abstract int eval02();
	public abstract int eval03();
	public abstract int eval04();
	
}
