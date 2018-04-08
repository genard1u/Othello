package othello.partie;

import java.util.ArrayList;
import java.util.Scanner;

import othello.Pion;
import othello.etat.Etat;
import othello.etat.EtatOthello;
import othello.eval.Eval0;
import othello.joueur.Joueur;
import othello.joueur.JoueurOthello;

public class PartieOthello extends Partie {
	
	private final static String LIGNE = "Entrez votre numéro de ligne : ";
	private final static String COLONNE = "Entrez votre numéro de colonne : ";
	private final static String COORDS_INVALIDES = "Coordonnées invalides !";
	private final static String AU_SUIVANT = "Au joueur suivant !";
	private final static String AUCUN_SUCCESSEUR = "Vous ne pouvez plus placer de pion !";
	
	private int passeSonTour;
	
	public PartieOthello(JoueurOthello un, JoueurOthello deux) {
		super(un, deux);
		
		assert j1().couleur() == Pion.NOIR;
		assert j2().couleur() == Pion.BLANC;
		
		etat = new EtatOthello();
		joueurCourant = j1;
		passeSonTour = 0;
	}
	
	public EtatOthello etat() {
		return (EtatOthello) super.etat;
	}
	
	public JoueurOthello j1() {
		return (JoueurOthello) super.j1;
	}
	
	public JoueurOthello j2() {
		return (JoueurOthello) super.j2;
	}
	
	public JoueurOthello joueurCourant() {
		return (JoueurOthello) super.joueurCourant;
	}
	
	public boolean joueursBloques() {
		return passeSonTour == 2;
	}
	
	/**
	 * @return partie terminée si les deux joueurs sont bloqués
	 */
	public boolean estTerminee() {
		boolean estTerminee = false;
		
		if (joueursBloques()) {
			estTerminee = true;
		}
		
		return estTerminee;
	}
	
    @SuppressWarnings("resource")
	private int[] entrerCoords() {
		Scanner sc = new Scanner(System.in);
		int[] coords = new int[2];
		
		System.out.println(LIGNE);
		coords[0] = sc.nextInt();
		
		System.out.println(COLONNE);
		coords[1] = sc.nextInt();
		
		return coords;
    }
    
    @Override
    protected void allerSurUnSuccesseur() {
    	EtatOthello etat = etat();
    	passeSonTour = 0;
		
		Pion couleur = joueurCourant().couleur();
		int[] coords = entrerCoords();
		int x = coords[0];
		int y = coords[1];
		
		while (! etat.estVide(x, y) || ! etat.successeur(x, y, couleur)) {
			System.out.println(COORDS_INVALIDES);
			coords = entrerCoords();
			x = coords[0];
			y = coords[1];
		}
		
		etat.setPion(x, y, couleur);
		etat.retourner(x, y, couleur);
	}

	@Override
	protected void aucunSuccesseur(boolean affichage) {
		passeSonTour ++;
		
		if (affichage) {
			System.out.println(AUCUN_SUCCESSEUR);
		}
	}
	
	protected void tour(int profondeur, boolean affichage, Eval0... eval0s) {
		ArrayList<Etat> succ = etat.successeurs(joueurCourant);
		if ( joueurCourant.estHumain()){
			System.out.println(etat.toString());
			
			if (succ.size() > 0) {
				allerSurUnSuccesseur();
			}
			else {
				aucunSuccesseur(affichage);
			}
		}
		else {
			if (affichage) {
				System.out.println(etat.toString());
			}
			
			if (eval0s.length == 2) {
				if (joueurCourant().couleur() == Pion.NOIR) {
					etat.setEval0(eval0s[0]);
				}
				else {
					etat.setEval0(eval0s[1]);
				}
			}
			
			Etat e = etat.minimax_alpha_beta(joueurCourant, j1, j2, profondeur);
			
			if (e == null) {
				aucunSuccesseur(affichage);
			}
			else {
				passeSonTour = 0;
				etat = e;
			}
		}
		
		if (!estTerminee()) {
			joueurSuivant(affichage);
		}
	}

	private void joueurSuivant(boolean affichage) {
		assert estTerminee() == false;
		
		if (joueurCourant == j1) {
			joueurCourant = j2;
		}
		else {
			joueurCourant = j1;
		}
		if (joueurCourant.estHumain() && affichage) {
			System.out.println(AU_SUIVANT);
		}	
	}

	/**
	 * @return joueur gagnant (null s'il n'y en a aucun)
	 */
	public Joueur gagnant() {	
		JoueurOthello j1 = j1(), j2 = j2();
		JoueurOthello gagnant = null;
		float gains = etat.valeurFinDePartie();
		
		assert estTerminee() == true;
		assert j1.couleur() != j2.couleur();
		
		if (gains == Float.MAX_VALUE) {
			if (j1.couleur() == Pion.NOIR) {
				gagnant = j1;
			}
			else {
				gagnant = j2;
			}
		}
		else if (gains == Float.MIN_VALUE) {
			if (j1.couleur() == Pion.NOIR) {
				gagnant = j2;
			}
			else {
				gagnant = j1;
			}
		}
		
		return gagnant;
	}
	
}
