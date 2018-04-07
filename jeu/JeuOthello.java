package othello.jeu;

import java.util.Scanner;

import othello.Pion;
import othello.eval.Eval0;
import othello.eval.Eval0Othello;
import othello.eval.Eval0Othello_1;
import othello.eval.Eval0Othello_2;
import othello.eval.Eval0Othello_3;
import othello.eval.Eval0Othello_4;
import othello.joueur.Joueur;
import othello.joueur.JoueurOthello;
import othello.partie.PartieOthello;

public class JeuOthello extends Jeu {

	public static final String PROFONDEUR = "Entrez une profondeur (>= 0)";
	public static final String PREMIERE_EVAL0 = "La première éval0 ";
	public static final String SECONDE_EVAL0 = "La seconde éval0 ";
	public static final String JOUER_OU_EVAL = "Voulez-vous jouer(0) ou faire une évaluation d'eval0(1) ? ";
	public static final String DEUX_EVAL0 = "Entrez le numéro de deux eval0 (entre 1 et 4) ";
	public static final String EVAL0_INVALIDE = "Eval0 non définie (doit être entre 1 et 4) ";
	public static final String JEU_AMI_MACHINE_DEUX_MACHINES = "Voulez-vous jouer contre un ami(0), une machine(1), ou voir une partie entre deux machines(2) ?";
	
	
	public JeuOthello() {
		j1 = new JoueurOthello("j1", false, Pion.NOIR);
		j2 = new JoueurOthello("j2", false, Pion.BLANC);

		Scanner sc = new Scanner(System.in);
		
		System.out.println(JOUER_OU_EVAL);
		
		boolean jouerOuEvaluation = choixBooleen(sc);
		
		if (jouerOuEvaluation) {
			System.out.println(DEUX_EVAL0);
			choixEval0(sc);
		}
		else {
			System.out.println(JEU_AMI_MACHINE_DEUX_MACHINES);
			choixJoueur(sc);			
		}
	}
	
	private void choixEval0(Scanner sc) {
		Eval0 e1, e2;
		
		System.out.println(PREMIERE_EVAL0);
		e1 = selectionEval0(sc);
		
		System.out.println(SECONDE_EVAL0);
		e2 = selectionEval0(sc);
		
		int profondeur = 0;
		
		do {
		    System.out.println(PROFONDEUR);
		    profondeur = sc.nextInt();
		} while (profondeur < 0);
		
		System.out.println(evaluationEval0(e1, e2, profondeur));		
	}

	private Eval0 selectionEval0(Scanner sc) {
		Eval0 eval0 = new Eval0Othello_1();
		int numEval0 = sc.nextInt();
		
		while (numEval0 < 1 || numEval0 > Eval0Othello.NB_EVAL0) {
			System.out.println(EVAL0_INVALIDE);
			numEval0 = sc.nextInt();
		}
		
		switch (numEval0) {
			case 1:
				eval0 = new Eval0Othello_1();
				break;
			case 2:
				eval0 = new Eval0Othello_2();
				break;
			case 3:
				eval0 = new Eval0Othello_3();
				break;
			case 4:
				eval0 = new Eval0Othello_4();
				break;
		}
		
		return eval0;
	}

	private void choixJoueur(Scanner sc) {
		int i = sc.nextInt();
		
		switch (i) {
			case 0 : 
				j1.setHumain(true);
				j2.setHumain(true);
				break;
			case 1 :
				j2.setHumain(true);
				break;
		}
		
		lancer(true);		
	}

	private boolean choixBooleen(Scanner sc) {
		if (sc.nextInt() == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean continuer() {
		System.out.println("fini, voulez vous recomencer? oui(1) non(sinon)");
		
		return choixBooleen(new Scanner(System.in));
	}
	
	@Override
	public void lancer(boolean aff) {        
		do {
			partieCourante = new PartieOthello((JoueurOthello) j1, (JoueurOthello) j2);
			Joueur gagnant = partieCourante.lancer(1, aff);
		
			gagnant.victoire();
			afficherScore(); 			
		} while (continuer());
	}
	
	public int evaluationEval0(Eval0 e1, Eval0 e2, int profondeur) {
		int res = 0;
		JoueurOthello jo1 = new JoueurOthello("m1", false, Pion.NOIR);
		JoueurOthello jo2 = new JoueurOthello("m2", false, Pion.BLANC);
		PartieOthello p = new PartieOthello(jo1, jo2);
		Joueur j = p.lancer(profondeur, false, e1, e2);
		 
		if (((JoueurOthello)j).couleur() == Pion.NOIR) {
			// System.out.println("11");
			res++;
		}
		else {
			// System.out.println("-11");
			res--;
		}
		
		/*((JoueurOthello)(p.j1)).setPion(Pion.BLANC);
		((JoueurOthello)(p.j2)).setPion(Pion.NOIR);*/
		
		p = new PartieOthello(jo1,jo2); 
		j = p.lancer(profondeur, false, e2, e1);
		 
		if (((JoueurOthello)j).couleur() == Pion.NOIR) {
			res--;
		}
		else {
			res++;
		}
		
		if (res > 1) {
			res = 1;
		}
		
		if (res < -1) {
			res = -1;
		}
		
		return res;
	}	

	public static void main(String[] args) {		
		// JeuOthello jeu = new JeuOthello();
		/*System.out.println("\nevaluation de Eval0Othello_1 et Eval0Othello_2 :");
		System.out.println(jeu.evaluationEval0(new Eval0Othello_1(), new Eval0Othello_2(), 2));
		System.out.println("\nevaluation de Eval0Othello_1 et Eval0Othello_3 :");
		System.out.println(jeu.evaluationEval0(new Eval0Othello_1(), new Eval0Othello_2(), 2));
		System.out.println("\n");
		System.out.println("\nevaluation de Eval0Othello_2 et Eval0Othello_3 :");
		System.out.println(jeu.evaluationEval0(new Eval0Othello_2(), new Eval0Othello_2(), 2));
		System.out.println("\n");
		System.out.println("\nevaluation de Eval0Othello_2 et Eval0Othello_1 :");
		System.out.println(jeu.evaluationEval0(new Eval0Othello_2(), new Eval0Othello_1(), 2));*/
		//jeu.lancer();
	}
	
}
