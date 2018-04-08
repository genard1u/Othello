package othello.amelioration;

import othello.Pion;
import othello.eval.Eval0;
import othello.joueur.JoueurOthello;
import othello.partie.PartieOthello;

public abstract class Amelioration {

	protected static final int PROFONDEUR_DEF = 2;
	
	protected int[][] tableDeNombres;
	protected int T;
	
	protected int profondeur;
	
	
	protected Amelioration(int taille) {
		tableDeNombres = tableDepart(taille);
		T = taille;
		profondeur = PROFONDEUR_DEF;
	}
	
	protected Amelioration(int taille, int profondeur) {
		tableDeNombres = tableDepart(taille);
		T = taille;
		this.profondeur = profondeur;
	}
	
	protected int[][] tableDepart(int T) {
		int[][] forces = new int[T][T];
	    
	    for (int i = 0; i < T * T; i ++) {
	    	int y = i / T;
	    	int x = i % T;
	    	
	    	if ((T % 2) == 0) {
	    		forces[y][x] = centrePair(T, y, x);
	    	}
	    	else {
	    		forces[y][x] = centreImpair(T, y, x);
	    	}
	    }
		
		return forces;
	}
	
	protected int centreImpair(int T, int y, int x) {
		int centre = T / 2 + 1;	    
	    
		return distanceDeManhattan(centre, x, centre, y);
	}
	
	protected int centrePair(int T, int y1, int x1) {
		assert T > 1;
			
		int x2 = 0;
		int y2 = 0;
		
		if (x1 < T / 2) {
			x2 = T / 2 - 1;
		}
		else {
			x2 = T / 2;
		}
		
		if (y1 < T / 2) {
			y2 = T / 2 - 1;
		}
		else {
			y2 = T / 2;
		}
		
		return distanceDeManhattan(x2, x1, y2, y1);
	}
	
	protected int distanceDeManhattan(int i1, int j1, int i2, int j2) {
		return Math.abs(i1 - i2) + Math.abs(j1 - j2);
	}
	
	protected int eval0VsEval0(JoueurOthello j1, JoueurOthello j2, Eval0 eval01, Eval0 eval02, int profondeur) {
		PartieOthello partie = null; 
		JoueurOthello gagnant = null;
		int gain = 0;
		
		partie = new PartieOthello(j1, j2);
		gagnant = (JoueurOthello) partie.lancer(profondeur, false, eval01, eval02);
		 
		if (gagnant != null) {
		    if (gagnant.couleur() == Pion.NOIR) {
			    gain ++;
		    }
		    else {
			    gain --;
		    }
		}
		
		return gain;
	}
	
	protected int evaluationEval0(Eval0 eval01, Eval0 eval02, int profondeur) {
		int gainsCumules = 0;
		JoueurOthello j1 = new JoueurOthello("m1", false, Pion.NOIR);
		JoueurOthello j2 = new JoueurOthello("m2", false, Pion.BLANC);
	
		gainsCumules += eval0VsEval0(j1, j2, eval01, eval02, profondeur);
		gainsCumules -= eval0VsEval0(j1, j2, eval02, eval01, profondeur);
		
		if (gainsCumules > 1) {
			gainsCumules = 1;
		}
		else if (gainsCumules < -1) {
			gainsCumules = -1;
		}
		
		return gainsCumules;
	}	
	
	public abstract void lancer();
	
}
