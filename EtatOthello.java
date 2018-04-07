package othello;

import java.util.ArrayList;

/**
 * @author Collignon Valentin
 * @author Genard Pierre
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
			
			plateau[x][y] = Pion.RIEN;
		}
	}
	
    private void premiersPions() {
    	setPion(T/2, T/2, Pion.BLANC);
    	setPion(T/2-1, T/2, Pion.NOIR);
    	setPion(T/2-1, T/2-1, Pion.BLANC);
    	setPion(T/2, T/2-1, Pion.NOIR);
	}

	public Pion[][] getPlateau() {
		Pion[][] p = new Pion[T][T];
		
		for (int i = 0 ; i < T * T; i ++){
			int y = i / T;
			int x = i % T;
			p[x][y] = plateau[x][y];
		}
		
		return p;
	}
	
	public int getTaille() {
		return T;
	}
	
	public void setPion(int x, int y, Pion p) {
		plateau[x][y] = p;
	}
	
	public boolean successeur(int x, int y, Pion p) {
		boolean successeur = false;
		for(int i=-1;i<2;i++){
			for (int j=-1;j<2;j++){		
				if (verifBord(x+i, y+j) && plateau[x+i][y+j]!= p && plateau[x+i][y+j]!= Pion.RIEN){
					int cx=x+i,cy=y+j;
					int[] r = verifOuRetourn(0,cx,cy,i,j,p);
					if (verifBord(r[0] ,r[1]) && plateau[r[0]][r[1]]==p){
						successeur=true;
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
				if (plateau[x][y]== Pion.RIEN){
					if (successeur(x, y, ((JoueurOthello)j).couleur())) {
						EtatOthello e = new EtatOthello(this);
						e.setPion(x, y, ((JoueurOthello)j).couleur());
						e.retourner(x, y,((JoueurOthello)j).couleur());
						successeurs.add(e);
					}
				}
			}
		}
		
		return successeurs;
	}
	
	public void retourner(int x, int y, Pion p) {
		// TODO Auto-generated method stub
		for(int i=-1;i<2;i++){
			for (int j=-1;j<2;j++){
				if(verifBord(x, y)){
					if (verifBord(x+i, y+j) &&plateau[x+i][y+j]!= p &&plateau[x+i][y+j]!= Pion.RIEN){
						int cx=x+i,cy=y+j;
						int[] r=verifOuRetourn(0,cx,cy,i,j,p);

						
						if(((r[0]>=0) && (r[1]>=0) && (r[0]<T) && (r[1]<T)) &&plateau[r[0]][r[1]]==p){
							 r=verifOuRetourn(1,r[0],r[1],i,j,p);

						}
					}
				}
			}
		}
	}
	
	public int[] verifOuRetourn(int mode , int x, int y, int i,int j,Pion p){
		int[] res= new int[2];
		int cx=x,cy=y;
		if(mode ==1)
		{
			cx=cx-i;
			cy=cy-j;
		}
		if(i!=0||j!=i){
			while(verifBord(cx, cy) && (plateau[cx][cy]!= p && plateau[cx][cy]!= Pion.RIEN)){
				switch (mode) {
				case 0:
					cx=cx+i;
					cy=cy+j;
					break;
				case 1:
					setPion(cx, cy,p);
					cx=cx-i;
					cy=cy-j;
					break;
				default:
					break;
				}
			}
		}
		res[0]=cx;
		res[1]=cy;
		return res;
	}
	
	public boolean verifBord(int x,int y){
		return(verifBordGauche(x) && verifBordHaut(y) && verifBordDroit(x) && verifBordBas(y));
	}
	
	public boolean verifBordGauche(int x){
		return(x>=0);
	}
	
	public boolean verifBordDroit(int x){
		return(x<=T-1);
	}
	
	public boolean verifBordHaut(int y){
		return(y>=0);
	}
	
	public boolean verifBordBas(int y){
		return(y<=T-1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		
		sb.append("   ");
		
		for (int y = 0; y < plateau.length; y ++) {
			sb.append(y + " ");
		}
		
		sb.append("\n");
		
		for (int y = 0; y < plateau.length; y ++) {
			sb.append(y + " |");
			
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
	
	public boolean estVide(int x, int y) {
		if (x>=0 && x<T && y>=0 && y<T){
			return plateau[x][y] == Pion.RIEN;
		}
		else {
			return false;
		}
	}
	
	public int nbJeton(Pion couleur) {
		int cmp = 0;
		
		for (int i = 0; i < T; i ++){
			for (int j = 0; j < T; j++){
				if (plateau[i][j] == couleur) {
					cmp++;
				}
			}
		}
		
		return cmp;
	}
	public int valJeton(Pion p) {
		int cmp = 0;
		
		for (int i = 0; i < T; i ++){
			for (int j = 0; j < T; j++){
				if (plateau[i][j] == p) {
					cmp++;
					if(estBord(i,j)){
						cmp++;
						if(estCoin(i,j)){
							cmp++;
						}
					}
				}
			}
		}
		return 0;
	}
	
	public int[][] forcesDesPositions() {
		int[][] forces = new int[T][T];
	    int centre = 0;
	    
	    if ((T % 2) == 1) {
	    	centre = T / 2 + T % 2;
	    }
	    
	    int xMilieu = centre;
	    int yMilieu = centre;
	    
	    for (int i = 0; i < T * T; i ++) {
	    	int y = i / T;
	    	int x = i % T;
	    	
	    	if ((T % 2) == 1) {
	    		forces[y][x] = distanceDeManhattan(xMilieu, x, yMilieu, y);
	    	}
	    }
		
		return forces;
	}
	
	public int forces(Pion couleur) {
		int[][] forces = forcesDesPositions();
		int eval = 0;
		
		for (int i = 0; i < T; i ++) {
    		for (int j = 0; j < T; j ++) {
    			if (plateau[i][j] == couleur) {
    				eval += forces[i][j];
    			}
    		}
    	}
		
		return eval;
	}
	
	public int distanceDeManhattan(int i1, int j1, int i2, int j2) {
		return Math.abs(i1 - i2) + Math.abs(j1 - j2);
	}
	
	private boolean estCoin(int i, int j) {
		return ((i == 0) || (i == T-1)) && ((j == 0) || (j == T-1));
	}

	private boolean estBord(int i, int j) {
		return (i == 0) || (i == T-1) || (j == 0) || (j == T-1);
	}

	public int eval01() {
		return nbJeton(Pion.NOIR)-nbJeton(Pion.BLANC);
	}
	
	public int eval02() {
		return valJeton(Pion.NOIR)-valJeton(Pion.BLANC);
	}
	
	public int eval03() {
		return (valJeton(Pion.NOIR)+nbJeton(Pion.NOIR))-(valJeton(Pion.BLANC)+nbJeton(Pion.BLANC));
	}
	
    public int eval04() {
    	return forces(Pion.NOIR) - forces(Pion.BLANC);
    }
    
	public static void main(String[] args) {
		EtatOthello test = new EtatOthello();
		JoueurOthello j0 = new JoueurOthello("j1", Pion.NOIR);
		JoueurOthello j1 = new JoueurOthello("j2", Pion.BLANC);
		
		test.setPion(2, 1, Pion.NOIR);
		test.setPion(1, 1, Pion.BLANC);
		test.setPion(2, 0, Pion.NOIR);
		test.setPion(1, 0, Pion.BLANC);
		test.setPion(3, 4, Pion.BLANC);
		test.setPion(4, 5, Pion.NOIR);
		
		System.out.println(test.toString());
		
		ArrayList<Etat> succ = test.successeurs(j0);
		
		for (Etat e :succ) {
			System.out.println(e.toString());
		}
		
		assert test.successeur(2, 3, j0.couleur());
		assert succ.size() > 0;
	}

}
