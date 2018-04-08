package othello.etat;

import java.util.ArrayList;

import othello.eval.Eval0;
import othello.eval.Eval0Othello_1;
import othello.joueur.Joueur;

/**
 * @author Collignon Valentin
 * @author Génard Pierre
 */
public abstract class Etat {

	protected Eval0 eval0;
	
	
	protected Etat() {
		eval0 = new Eval0Othello_1();
	}
	
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
	
	public Eval0 getEval0() {
		return eval0;
	}
	
	public void setEval0(Eval0 eval) {
		eval0 = eval;
	}
	
	public abstract boolean estPremier(Joueur j);
	public abstract float valeurFinDePartie();
	
	public Joueur joueurSuivant(Joueur courant, Joueur j1, Joueur j2) {
		Joueur suivant = null;
		
		if (courant == j1) {
			suivant = j2;
		}
		else if (courant == j2) {
			suivant = j1;
		}
		
		assert false;
		
		return suivant;
	}
	
	public Etat minimax(Joueur courant, Joueur j1, Joueur j2, int c) {
		ArrayList<Etat> S = successeurs(courant);
		Etat eSortie = null;
		float score_max = Float.MIN_VALUE;
		float score_min = Float.MAX_VALUE;
		float score = 0f;
		
		for (Etat e : S) {
			score = evaluation(courant, j1, j2, c);
			
			if (estPremier(courant)) {
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
		if (estFinal(courant)) {
			return valeurFinDePartie();
		}
		
		if (c == 0) {
			return eval0.eval(this);
		}
		
		Joueur suivant = joueurSuivant(courant, j1, j2);
		ArrayList<Etat> S = successeurs(courant);
		
		if (estPremier(courant)) {
			float score_max = Float.MIN_VALUE;
			
			for (Etat e : S) {
				score_max = Math.max(score_max, e.evaluation(suivant, j1, j2, c - 1));
			}
			
			return score_max;
		}
		else {
			float score_min = Float.MAX_VALUE;
			
			for (Etat e : S) {
				score_min = Math.min(score_min, e.evaluation(suivant, j1, j2, c - 1));
			}
			
			return score_min;
		}
	}
	
	public Etat minimax_alpha_beta(Joueur courant, Joueur j1, Joueur j2, int c) {
		ArrayList<Etat> S = successeurs(courant);
		Etat eSortie = null;
		float score_min = Float.MAX_VALUE;
		float score_max = Float.MIN_VALUE;
		float score = 0f;
		
		for (Etat e : S) {
			score = evaluation_alpha_beta(courant, j1, j2, c, Float.MIN_VALUE, Float.MAX_VALUE);
			
			if (estPremier(courant)) {
				if (score >= score_max) {
					eSortie = e;
					score_max = score;
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
	
	public float evaluation_alpha_beta(Joueur courant, Joueur j1, Joueur j2, int c, float alpha, float beta) {
		if (estFinal(courant)) {
			return valeurFinDePartie();
		}
		
		if (c == 0) {
			return eval0.eval(this);
		}
		
		Joueur suivant = joueurSuivant(courant, j1, j2);
		ArrayList<Etat> S = successeurs(courant);
		
		if (estPremier(courant)) {
			float score_max = Float.MIN_VALUE;
			
			for (Etat e : S) {
				score_max = Math.max(score_max, e.evaluation_alpha_beta(suivant, j1, j2, c - 1, alpha, beta));
				
				if (score_max >= beta) {
					return score_max;
				}
				
				alpha = Math.max(alpha, score_max);
			}
			
			return score_max;
		}
		else {
			float score_min = Float.MAX_VALUE;
			
			for (Etat e : S) {
				score_min = Math.min(score_min, e.evaluation_alpha_beta(suivant, j1, j2, c - 1, alpha, beta));
				
				if (score_min <= alpha) {
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
