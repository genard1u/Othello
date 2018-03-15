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
				System.out.println("succ3");
				if(verifBord(i, j, x, y)){
					System.out.println("succ2");
					if (plateau[x+i][y+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN){
						int cx=x+i,cy=y+j;
						int[]r = verifOuRetourn(0,cx,cy,i,j,p);
						if(((r[0]>=0) && (r[1]>=0) && (r[0]<T) && (r[1]<T)) &&plateau[r[0]][r[1]]==p){
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
				if(verifBord(i, j, x, y)){
					if (plateau[x+i][y+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN){
						int cx=x+i,cy=y+j;
						
						verifOuRetourn(0,cx,cy,i,j,p);
						if(((cx>=0) && (cy>=0) && (cx<T) && (cy<T)) &&plateau[cx][cy]==p){
							
							verifOuRetourn(1,cx,cy,i,j,p);

						}
					}
				}
			}
		}
	}
	
	public int[] verifOuRetourn(int mode , int x, int y, int i,int j,Pion p){
		int[] res= new int[2];
		int cx=x,cy=y;

		while(verifBord(i, j, cx, cy) && (plateau[cx+i][cy+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN)){
			switch (mode) {
			case 0:
				cx=cx+i;
				cy=cy+j;
				break;
			case 1:
				cx=cx-i;
				cy=cy-j;
				setPlateau(cx, cy,p);
				break;
			default:
				break;
			}
		}
		res[0]=cx;
		res[1]=cy;
		return res;
	}
	public boolean verifBord(int i,int j,int x,int y){
		return(verifBordGauche(i, x) && verifBordHaut(j, y) && verifBordDroit(i, x) && verifBordBas(j, y));
	}
	
	public boolean verifBordGauche(int i,int x){
		return(i==-1 && x!=0);
	}
	public boolean verifBordDroit(int i,int x){
		return(i==1 && x!=T-1);
	}
	public boolean verifBordHaut(int j,int y){
		return(j==-1 && y!=0);
	}
	public boolean verifBordBas(int j,int y){
		return(j==1 && y!=T-1);
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
