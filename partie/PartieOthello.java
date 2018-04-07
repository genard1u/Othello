package othello.partie;

import java.util.ArrayList;
import java.util.Scanner;

import othello.Pion;
import othello.etat.Etat;
import othello.etat.EtatOthello;
import othello.eval.Eval0;
import othello.eval.Eval0Othello_1;
import othello.eval.Eval0Othello_3;
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
		
		assert un.couleur() != deux.couleur();
		
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
		etat.retourner(x, y,couleur);
	}

	@Override
	protected void aucunSuccesseur(boolean affichage) {
		passeSonTour ++;
		
		if (affichage) {
			System.out.println(AUCUN_SUCCESSEUR);
		}
	}
	
	protected void tour(int c, boolean affichage, Eval0... eval0s) {
		ArrayList<Etat> succ = etat.successeurs(joueurCourant);
		
		if (joueurCourant.estHumain()) {
			System.out.println(etat.toString());
			
			if (succ.size() > 0) {
				allerSurUnSuccesseur();
			}
			else {
				aucunSuccesseur(affichage);
			}
		}
		else{
			if (affichage) {
				System.out.println(etat.toString());
			}
			
			if (eval0s.length == 2) {
				if (((JoueurOthello) joueurCourant).couleur() == Pion.NOIR){
					setEval0(eval0s[0]);
				}
				else{
					setEval0(eval0s[1]);
				}
			}
			
			Etat e = etat.minimax_alpha_beta(joueurCourant, j1, j2, c);
			
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
	
	private void setEval0(Eval0 eval) {
		eval0 = eval;
	}

	private void selectionSucesseur(ArrayList<Etat> succ) {
		Etat m = succ.get(0);
		
		if (((JoueurOthello) joueurCourant).couleur() == Pion.NOIR) {
			for (Etat e : succ){
				if (eval0.eval(e) > eval0.eval(m)){
					m = e ;
				}
			}
		}
		else {
			for (Etat e : succ){
				if (eval0.eval(e) < eval0.eval(m)){
					m = e ;
				}
			}
		}
		
		etat = m;
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

	public Joueur getGagnant() {
		EtatOthello etat = etat();
		JoueurOthello j1 = j1(), j2 = j2();
		int cmpJetonJ1 = 0 , cmpJetonJ2 = 0;
		
	    cmpJetonJ1 = etat.nbJetons(j1.couleur());
	    cmpJetonJ2 = etat.nbJetons(j2.couleur());
	    
		if (cmpJetonJ1 < cmpJetonJ2) {
			return j2;
		}
		else {
			return j1;
		}
	}
	
}
