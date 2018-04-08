package othello.etat;

import java.util.ArrayList;

import othello.Pion;
import othello.eval.Eval0;
import othello.eval.Eval0Othello_1;
import othello.eval.Eval0Othello_2;
import othello.eval.Eval0Othello_3;
import othello.eval.Eval0Othello_4;
import othello.eval.Eval0Othello_5;
import othello.joueur.Joueur;
import othello.joueur.JoueurOthello;

/**
 * @author Collignon Valentin
 * @author Génard Pierre
 */
public abstract class Etat {

	protected Eval0 eval0;
	
	
	protected Etat() {
		eval0 = new Eval0Othello_2();
	}
	
	protected Etat(Eval0 e) {
		eval0 = e;
	}
	
	public boolean equals(Etat e) {
		return toString().equals(e.toString());
	}
	
	public boolean estFinal(Joueur j1, Joueur j2) {
		ArrayList<Etat> S1 = successeurs(j1);
		ArrayList<Etat> S2 = successeurs(j2);
		boolean estFinal = false;
		
		if (S1.size() == 0 && S2.size() == 0) {
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
	
	public abstract boolean joueurMaximisant(Joueur j);
	public abstract float valeurFinDePartie();
	
	public Joueur joueurSuivant(Joueur courant, Joueur j1, Joueur j2) {
		Joueur suivant = null;
		
		if (courant == j1) {
			suivant = j2;
		}
		else if (courant == j2) {
			suivant = j1;
		}
		
		assert suivant != null;
		
		return suivant;
	}
	
	public Etat minimax(Joueur courant, Joueur j1, Joueur j2, int c) {
		ArrayList<Etat> S = successeurs(courant);
		Joueur suivant = joueurSuivant(courant, j1, j2);
		Etat eSortie = null;
		float score_max = Integer.MIN_VALUE;
		float score_min = Integer.MAX_VALUE;
		float score = 0f;
		
		assert c > 0;
		
		for (Etat e : S) {			
			score = e.evaluation(suivant, j1, j2, c - 1);
			
			if (joueurMaximisant(courant)) {
				
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
	
	public float evaluation(Joueur courant, Joueur j1, Joueur j2, int c) {
		if (estFinal(j1, j2)) {
			return valeurFinDePartie();
		}
		
		
		if (c == 0) {
			return eval0.eval(this);
		}
		
		Joueur suivant = joueurSuivant(courant, j1, j2);
		ArrayList<Etat> S = successeurs(courant);
		
		if (joueurMaximisant(courant)) {
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
		Joueur suivant = joueurSuivant(courant, j1, j2);
		Etat eSortie = null;
		float score_min = Integer.MAX_VALUE;
		float score_max = Integer.MIN_VALUE;
		float score = 0f;
		
		assert c > 0;
		
		for (Etat e : S) {
			score = e.evaluation_alpha_beta(suivant, j1, j2, c - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
			if (joueurMaximisant(courant)) {
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
		if (estFinal(j1, j2)) {
			return valeurFinDePartie();
		}
		
		if (c == 0) {
			return eval0.eval(this);
		}
		
		Joueur suivant = joueurSuivant(courant, j1, j2);
		ArrayList<Etat> S = successeurs(courant);
		if (joueurMaximisant(courant)) {
			float score_max = Integer.MIN_VALUE;
			
			for (Etat e : S) {
				e.setEval0(eval0);
				score_max = Math.max(score_max, e.evaluation_alpha_beta(suivant, j1, j2, c - 1, alpha, beta));
				
				if (score_max >= beta) {
					return score_max;
				}
				
				alpha = Math.max(alpha, score_max);
			}
			
			return score_max;
		}
		else {
			float score_min = Integer.MAX_VALUE;
			
			for (Etat e : S) {
				e.setEval0(eval0);
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
	public abstract int eval05();
	
	public static void main(String[] args) {	
		JoueurOthello j1 = new JoueurOthello("m1", false, Pion.NOIR);
		JoueurOthello j2 = new JoueurOthello("m2", false, Pion.BLANC);
		JoueurOthello courant = j1;
		Eval0Othello_1 eval01 = new Eval0Othello_1();
		Eval0Othello_2 eval02 = new Eval0Othello_2();
		Eval0Othello_3 eval03 = new Eval0Othello_3();
		Eval0Othello_4 eval04 = new Eval0Othello_4();
		Eval0Othello_5 eval05 = new Eval0Othello_5();
		EtatOthello etat = new EtatOthello();
		int profondeur = 1;
		
		etat.setPion(1, 3, Pion.BLANC);
		etat.setPion(2, 3, Pion.BLANC);				
		etat.setPion(5, 0, Pion.BLANC);
		
		for (int i = 1; i < etat.getTaille() - 1; i ++) {
			etat.setPion(5, i, Pion.BLANC);
		}
		
		System.out.println(etat.toString());
		
        /* ArrayList<Etat> succ = etat.successeurs(courant);
		
		for (Etat e :succ) {
			System.out.println(e.toString());
		} */
		
		etat.setEval0(eval01);
		System.out.println(etat.minimax(courant, j1, j2, profondeur));
		
		/* etat.setEval0(eval02);
		System.out.println(etat.minimax(courant, j1, j2, profondeur));
		
		etat.setEval0(eval03);
		System.out.println(etat.minimax(courant, j1, j2, profondeur));
		
		etat.setEval0(eval04);
		System.out.println(etat.minimax(courant, j1, j2, profondeur));
		
		etat.setEval0(eval05);
		System.out.println(etat.minimax(courant, j1, j2, profondeur)); */
	}
	
}
