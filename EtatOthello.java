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

	private final static int T_DEF = 8;
	
	private int T = T_DEF;	
	private Pion[][] plateau;
	
	
	public EtatOthello() {
		plateau = new Pion[T][T];
		plateauVide();
		premiersPions();
	}
	
	public EtatOthello(int taille) {
		T = taille;
		plateau = new Pion[T][T];
		plateauVide();
		premiersPions();
	}
	
	public EtatOthello(EtatOthello e) {
		plateau = e.getPlateau();
		T = e.getTaille();
	}
	
	private void plateauVide() {
		for (int i = 0 ; i < T * T; i ++){
			int y = i / T;
			int x = i % T;
			
			plateau[y][x] = Pion.RIEN;
		}
	}
	
    private void premiersPions() {
    	plateau[T/2][T/2] = Pion.BLANC;
		plateau[T/2][T/2-1] = Pion.NOIR;
		plateau[T/2-1][T/2-1] = Pion.BLANC;
		plateau[T/2-1][T/2] = Pion.NOIR;
	}

	public Pion[][] getPlateau() {
		Pion [][] p = new Pion[T][T];
		
		for (int i = 0 ; i < T * T; i ++){
			int y = i / T;
			int x = i % T;
			p[y][x] = plateau[y][x];
		}
		
		return p;
	}
	
	public int getTaille() {
		return T;
	}
	
	public void setPlateau(int x, int y, Pion p) {
		plateau[x][y] = p;
	}
	
	public boolean successeur(int x, int y, Pion p) {
		boolean successeur = false;
		for(int i=-1;i<2;i++){
			for (int j=-1;j<2;j++){
				if((i==-1 && x!=0) && (j==-1 && y!=0) && (i==1 && x!=T-1) && (j==1 && y!=T-1)){
					if (plateau[x+i][y+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN){
						int cx=x+i,cy=y+j;
						while(((i==-1 && cx!=0) && (j==-1 && cy!=0) && (i==1 && cx!=T-1) && (j==1 && cy!=T-1)) && (plateau[cx+i][cy+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN)){
							 cx=cx+i;
							 cy=cy+j;
						}
						if(((cx>=0) && (cy>=0) && (cx<T) && (cy<T)) &&plateau[cx][cy]==p){
							successeur=true;
						}
					}
				}
			}
		}
		return successeur;
	}
	
	/* (non-Javadoc)
	 * @see othello.Etat#successeurs()
	 */
	@Override
	public ArrayList<Etat> successeurs(Joueur j) {
		ArrayList<Etat> successeurs = new ArrayList<Etat>();
		
		for (int y = 0; y < getTaille(); y ++) {
			for (int x = 0; x < getTaille(); x ++) {
				if (successeur(x, y, ((JoueurOthello)j).getPion())) {
					EtatOthello e = new EtatOthello(this);
					System.out.println("succ");
					e.setPlateau(x, y, ((JoueurOthello)j).getPion());
					e.retourner(x,y,((JoueurOthello)j).getPion());
					successeurs.add(e);
				}
			}
		}
		
		return successeurs;
	}
	
	private void retourner(int x, int y, Pion p) {
		// TODO Auto-generated method stub
		for(int i=-1;i<2;i++){
			for (int j=-1;j<2;j++){
				if((i==-1 && x!=0) && (j==-1 && y!=0) && (i==1 && x!=T-1) && (j==1 && y!=T-1)){
					if (plateau[x+i][y+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN){
						int cx=x+i,cy=y+j;
						while(((i==-1 && cx!=0) && (j==-1 && cy!=0) && (i==1 && cx!=T-1) && (j==1 && cy!=T-1)) && (plateau[cx+i][cy+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN)){
							 cx=cx+i;
							 cy=cy+j;
						}
						if(((cx>=0) && (cy>=0) && (cx<T) && (cy<T)) &&plateau[cx][cy]==p){
							while(((i==-1 && cx!=0) && (j==-1 && cy!=0) && (i==1 && cx!=T-1) && (j==1 && cy!=T-1)) && (plateau[cx+i][cy+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN)){
								 cx=cx-i;
								 cy=cy-j;
								 
								 setPlateau(cx, cy,p);
							}
						}
					}
				}
			}
		}
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
		JoueurOthello j  = new  JoueurOthello(Pion.NOIR);
		ArrayList<Etat>s=e.successeurs(j);

		System.out.println(s.size());
		for (Etat es:s){
			System.out.println(es.toString());
		}
	}
	
}
