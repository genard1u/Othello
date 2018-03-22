package othello;

import java.util.Scanner;


public class PartieOthello extends Partie {
	
	private int passeSonTour;
	
	
	public PartieOthello(Joueur un, Joueur deux) {
		super(un, deux);
		((JoueurOthello) j1).setPion(Pion.NOIR);
		((JoueurOthello) j2).setPion(Pion.BLANC);
		joueurCourant = j1;
	}
	
	public boolean joueursBloques() {
		return passeSonTour == 2;
	}
	
	public boolean estTerminee() {
		boolean estGagnee = false;
		
		if (joueursBloques()) {
			estGagnee = true;
		}
		
		return estGagnee;
	}
	

	protected void tour(){
		if( etat.successeurs(joueurCourant).size()>0){
			// metre passer son tour a 0
			System.out.println(etat.toString());
			int x=-1,y=-1;
			Scanner sc = new Scanner(System.in);
			System.out.println("entrer votre numero de ligne");
			x = sc.nextInt();
			System.out.println("entrer votre numero de colonne");
			y = sc.nextInt();
			while (!((EtatOthello)etat).successeur(x, y, ((JoueurOthello)joueurCourant).getPion())) {
				System.out.println("coordonnée invalide!");
				System.out.println("entrer votre numero de ligne");
				x = sc.nextInt();
				System.out.println("entrer votre numero de colonne");
				y = sc.nextInt();
			}
			((EtatOthello)etat).setPion(x, y, ((JoueurOthello)joueurCourant).getPion());
			((EtatOthello)etat).retourner(x, y,((JoueurOthello)joueurCourant).getPion());
			
			
		}else{
			//passer un tour
			System.out.println("Vous ne pouvez pas plaser de pion !! ");
		}
		
		joueurSuivant();
		
	}
	
	
	private void joueurSuivant() {
		System.out.println("Au joueur suivant ! ");
		if (joueurCourant == j1){
			joueurCourant = j2;
		}else{
			joueurCourant = j1;
		}
	}


	protected Joueur getGagnant(){
		return j1;
	}
	
	public static void main(String[] args) {
		PartieOthello p = new PartieOthello(new JoueurOthello(), new JoueurOthello());
		
		p.jeu();
	}
	
}
