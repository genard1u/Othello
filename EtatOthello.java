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

	private final static int T = 8;
	
	public enum Pion{
		RIEN, BLANC, NOIR
	};
	
	private Pion[][] plateau;	
	
	
	/**
	 * 
	 */
	public EtatOthello() {
		plateau = new Pion[T][T];
		
		for (int i= 0 ; i<T*T;i++){
			int y = i/T;
			int x = i%T;
			
			plateau[y][x]= Pion.RIEN;
		}
		
		plateau[T/2][T/2]= Pion.BLANC;
		plateau[T/2][T/2-1]= Pion.NOIR;
		plateau[T/2-1][T/2-1]= Pion.BLANC;
		plateau[T/2-1][T/2]= Pion.NOIR;
	}
	
	public EtatOthello(int t) {
		plateau = new Pion[t][t];
		
		for (int i= 0 ; i<t*t;i++){
			int y = i/t;
			int x = i%t;
			
			plateau[y][x]= Pion.RIEN;
		}
		
		plateau[t/2][t/2]= Pion.BLANC;
		plateau[t/2][t/2-1]= Pion.NOIR;
		plateau[t/2-1][t/2-1]= Pion.BLANC;
		plateau[t/2-1][t/2]= Pion.NOIR;
	}
	
	
	public EtatOthello(EtatOthello e) {
		plateau = e.getPlateau();
	}
	
	public Pion [][] getPlateau() {
		int t = getTaille();
		Pion [][] p = new Pion[t][t];
		
		for (int i= 0 ; i<t*t;i++){
			int y = i/t;
			int x = i%t;
			p[y][x]= plateau[y][x];
		}
		
		return p;
	}
	
	public int getTaille() {
		return plateau.length;
	}
	
	public void setPlateau(int x, int y, Pion p) {
		plateau[x][y] = p;
	}
	
	/* (non-Javadoc)
	 * @see othello.Etat#successeurs()
	 */
	@Override
	public ArrayList<Etat> successeurs(Joueur j) {
		ArrayList<Etat> successeurs = new ArrayList<Etat>();
		
		return successeurs;
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
	
	public static void main(String[] args) {
		Etat e = new EtatOthello();
		
		System.out.println(e.toString());
	}
	
}
