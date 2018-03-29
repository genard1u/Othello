/**
 * 
 */
package othello;

import java.util.ArrayList;

/**
 * @author Collignon Valentin
 * @author Genard Pierre
 */
public abstract class Etat {

	protected Etat() {}
		
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
	
	public Etat minimax(Joueur j, int c) {
		ArrayList<Etat> S = successeurs(j);
		Etat eSortie = null;
		float score = 0;
		float score_max = Float.MAX_VALUE;
		
		
		return eSortie;
	}
	
	public float evaluation(Etat e, Joueur j, int c) {
		ArrayList<Etat> S;
		float score = 0;
		float score_max = 0;
		float score_min = 0;
		
		if (estFinal(j)) {
			
		}
		
		if (c == 0) {
			return eval01();
		}
		
		S = successeurs(j);
		
		if (j.estMachine()) {
			score_max = Float.MIN_VALUE;
			
			for (Etat s : S) {
				score_max = Math.max(score_max, evaluation());
			}
			
			return score_max;
		}
		else {
			score_min = Float.MAX_VALUE;
			
			for (Etat s : S) {
				score_max = Math.min(score_max, evaluation());
			}
			
			return score_min;
		}
	}
	
	public abstract int eval0();
	public abstract ArrayList<Etat> successeurs(Joueur j);

	public abstract int eval01();
	public abstract int eval02();
	public abstract int eval03();
	
}
