package othello.amelioration;

import java.util.Random;

import othello.Pion;
import othello.etat.EtatOthello;
import othello.jeu.JeuOthello;

public class Triviale extends Amelioration {

	protected static final Random alea = new Random();
	
	protected static final int CHANGEMENT = 2;
	
	
	public Triviale(int T) {
		super(T);
	}

	protected int[][] petiteModification() {
		int[][] tableModifiee = new int[T][T];
		
		for (int i = 0; i < T * T; i ++) {
			int y = i / T;
			int x = i % T;
			
			tableModifiee[y][x] = tableDeNombres[y][x];
		}
		
		int x = alea.nextInt(T);
		int y = alea.nextInt(T);
		
		tableModifiee[y][x] += CHANGEMENT;
		
		return tableModifiee;
	}
	
	protected int forces(EtatOthello etat, Pion couleur) {
		Pion[][] plateau = etat.getPlateau();
		int eval = 0;
		
		for (int i = 0; i < T; i ++) {
    		for (int j = 0; j < T; j ++) {
    			if (plateau[i][j] == couleur) {
    				eval += tableDeNombres[i][j];
    			}
    		}
    	}
		
		return eval;
	}
	
	@Override
	public void lancer() {
		JeuOthello jeu = new JeuOthello();
		int[][] tableModifiee = petiteModification();
	}

}
