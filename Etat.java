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
		float score_max = Float.MIN_VALUE;
		for (Etat e : S){
			score = evaluation(c,j);
			if (score >= score_max)
			{
				eSortie = e;
				score_max= score ;
			}
		}
			
		return eSortie;
	}
	
	public float evaluation(int c,Joueur j) {
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
		
		if (j.estMachine()) {//TODO
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
	
	public abstract ArrayList<Etat> successeurs(Joueur j);

	public abstract int eval01();
	public abstract int eval02();
	public abstract int eval03();
	
}
