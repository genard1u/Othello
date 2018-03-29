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
	
	public float evaluation(Joueur j, int c) {
		ArrayList<Etat> S;
		float score = 0;
		float score_max = 0;
		float score_min = 0;
		
		if (estFinal(j)) {
			
		}
		
		if (c == 0) {
			return e.eval01();
		}
		
		S = successeurs(j);
		return 0;
	}
	
	public abstract int eval0();
	public abstract ArrayList<Etat> successeurs(Joueur j);

	public abstract int eval01();
	public abstract int eval02();
	public abstract int eval03();
	
}
