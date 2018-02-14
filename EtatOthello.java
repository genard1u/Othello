/**
 * 
 */
package othello;

import java.util.ArrayList;

/**
 * @author Collignon Valentin
 * @author Genard Pierre
 *
 */
public class EtatOthello extends Etat {

	private final static int TAILLE=8;
	
	private Pion [][] plateau;
	
	public enum Pion{
		RIEN, BLANC, NOIR
	};
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
		plateau[t/2][t/2]= Pion.BLANC;
		plateau[t/2][(t/2)-1]= Pion.NOIR;
		plateau[(t/2)-1][(t/2)-1]= Pion.BLANC;
		plateau[(t/2)-1][t/2]= Pion.NOIR;
	}
	
	
	public EtatOthello(EtatOthello e) {
		// TODO Auto-generated constructor stub
		
		plateau = e.getPlateau();
	}
	
	public Pion [][] getPlateau(){
		int t = getTaille();
		Pion [][] p = new Pion[t][t];
		for (int i= 0 ; i<t*t;i++){
			int y = i/t;
			int x = i%t;
			p[y][x]= plateau[y][x];
		}
		return p;
	}
	
	public int getTaille(){
		return plateau.length;
	}
	
	public void setPlateau(int x, int y, Pion p){
		plateau[x][y]=p;
	}
	/* (non-Javadoc)
	 * @see othello.Etat#successeurs()
	 */
	@Override
	public ArrayList<Etat> successeurs() {
		// TODO Auto-generated method stub
		return null;
	}

}
