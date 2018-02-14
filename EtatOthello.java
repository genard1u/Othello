/**
 * 
 */
package othello;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Collignon Valentin
 * @author Genard Pierre
 *
 */
public class EtatOthello extends Etat {

	private final static int TAILLE=8;
	
	public enum Pion{
		RIEN, BLANC, NOIR
	};
	
	private Pion[][] plateau;	
	
	
	/**
	 * 
	 */
	public EtatOthello() {
		// TODO Auto-generated constructor stub
		plateau = new Pion[TAILLE][TAILLE];
		for (int i= 0 ; i<TAILLE*TAILLE;i++){
			int y = i/TAILLE;
			int x = i%TAILLE;
			plateau[y][x]= Pion.RIEN;
		}
		plateau[TAILLE/2][TAILLE/2]= Pion.BLANC;
		plateau[TAILLE/2][(TAILLE/2)-1]= Pion.NOIR;
		plateau[(TAILLE/2)-1][(TAILLE/2)-1]= Pion.BLANC;
		plateau[(TAILLE/2)-1][TAILLE/2]= Pion.NOIR;
	}
	
	public EtatOthello(int t) {
		// TODO Auto-generated constructor stub
		plateau = new Pion[t][t];
		for (int i= 0 ; i<t*t;i++){
			int y = i/t;
			int x = i%t;
			plateau[y][x]= Pion.RIEN;
		}
		plateau[TAILLE/2][t/2]= Pion.BLANC;
		plateau[TAILLE/2][(t/2)-1]= Pion.NOIR;
		plateau[(TAILLE/2)-1][(t/2)-1]= Pion.BLANC;
		plateau[(TAILLE/2)-1][TAILLE/2]= Pion.NOIR;
	}

	/* (non-Javadoc)
	 * @see othello.Etat#successeurs()
	 */
	@Override
	public ArrayList<Etat> successeurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		
		for (int y = 0; y < plateau.length; y ++) {
			sb.append("|");
			
			for (int x = 0; x < plateau[0].length; x ++) {
				switch (plateau[y][x]) {
				    case RIEN:
				        sb.append(" |");
				        break;
				    case BLANC:
				    	sb.append("B|");
				        break;
				    case NOIR:
				    	sb.append("N|");
				        break;
				}
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
				
	}
	
}
