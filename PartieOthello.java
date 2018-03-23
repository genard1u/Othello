package othello;

import java.util.ArrayList;
import java.util.Scanner;

public class PartieOthello extends Partie {
	
	private final static String LIGNE = "Entrez votre numéro de ligne : ";
	private final static String COLONNE = "Entrez votre numéro de colonne : ";
	private final static String COORDS_INVALIDES = "Coordonnées invalides !";
	private final static String AU_SUIVANT = "Au joueur suivant !";
	private final static String AUCUN_SUCCESSEUR = "Vous ne pouvez plus placer de pion !";
	
	private int passeSonTour;
	
	
	public PartieOthello(Joueur un, Joueur deux) {
		super(un, deux);
		((JoueurOthello) j1).setPion(Pion.NOIR);
		((JoueurOthello) j2).setPion(Pion.BLANC);
		joueurCourant = j1;
		passeSonTour = 0;
	}
	
	public boolean joueursBloques() {
		return passeSonTour == 2;
	}
	
	public boolean estTerminee() {
		boolean estTerminee = false;
		
		if (joueursBloques()) {
			estTerminee = true;
		}
		
		return estTerminee;
	}
	
    private int[] entrerCoords() {
		Scanner sc = new Scanner(System.in);
		int[] coords = new int[2];
		
		System.out.println(LIGNE);
		coords[0] = sc.nextInt();
		
		System.out.println(COLONNE);
		coords[1] = sc.nextInt();
		
		sc.close();
		
		return coords;
    }
    
    @Override
    protected void allerSurUnSuccesseur() {
    	passeSonTour = 0;
		System.out.println(etat.toString());
		
		Pion couleur = ((JoueurOthello) joueurCourant).couleur();
		int[] coords = entrerCoords();
		int x = coords[0];
		int y = coords[1];
		
		while (!((EtatOthello) etat).successeur(x, y, couleur)) {
			System.out.println(COORDS_INVALIDES);
			coords = entrerCoords();
			x = coords[0];
			y = coords[1];
		}
		
		((EtatOthello) etat).setPion(x, y, couleur);
		((EtatOthello) etat).retourner(x, y,couleur);
	}

	@Override
	protected void aucunSuccesseur() {
		passeSonTour ++;
		System.out.println(AUCUN_SUCCESSEUR);
	}
	
	protected void tour() {
		ArrayList<Etat> succ = etat.successeurs(joueurCourant);
		
		if (succ.size() > 0) {
			allerSurUnSuccesseur();
		}
		else {
			aucunSuccesseur();
		}
		
		joueurSuivant();
	}
	
	private void joueurSuivant() {
		System.out.println(AU_SUIVANT);
		
		if (joueurCourant == j1) {
			joueurCourant = j2;
		}
		else {
			joueurCourant = j1;
		}
	}
	
	public Joueur getGagnant() {
		return joueurCourant;
	}
	
}
