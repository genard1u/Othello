package othello;

import java.util.ArrayList;
import java.util.Scanner;


public class PartieOthello extends Partie {
	
	private int passeSonTour;
	
	
	public PartieOthello(Joueur un, Joueur deux) {
		super(un, deux);
		etat= new EtatOthello();
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
		System.out.println(etat.toString());
		if( ((EtatOthello)etat).successeurs(joueurCourant).size()>0){
			passeSonTour=0;
			
			int x=-1,y=-1;
			Scanner sc = new Scanner(System.in);
			System.out.println("entrer votre numero de ligne");
			x = sc.nextInt();
			System.out.println("entrer votre numero de colonne");
			y = sc.nextInt();
			while (!((EtatOthello)etat).estVide(x,y)|| (!((EtatOthello)etat).successeur(x, y, ((JoueurOthello)joueurCourant).getPion()))) {
				System.out.println("coordonnée invalide!");
				System.out.println("entrer votre numero de ligne");
				x = sc.nextInt();
				System.out.println("entrer votre numero de colonne");
				y = sc.nextInt();
			}
			((EtatOthello)etat).setPion(x, y, ((JoueurOthello)joueurCourant).getPion());
			((EtatOthello)etat).retourner(x, y,((JoueurOthello)joueurCourant).getPion());
			System.out.println(etat.toString());
			
		}else{
			passeSonTour++ ;
			System.out.println("Vous ne pouvez pas plaser de pion !! ");
		}
		if (!estTerminee()){
			joueurSuivant();
		}else {
			System.out.println("Partie fini !!!");
			System.out.println("le gagnant est :"+getGagnant());
		}
		
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
		int cmpJetonJ1 = 0 , cmpJetonJ2 = 0;
	    cmpJetonJ1 = ((EtatOthello)etat).nbJeton(((JoueurOthello)j1).getPion());
	    cmpJetonJ2 = ((EtatOthello)etat).nbJeton(((JoueurOthello)j2).getPion());
		if (cmpJetonJ1 < cmpJetonJ2){
			return j2;
		}else{
			return j1;
		}
	}
	
	public static void main(String[] args) {
		PartieOthello p = new PartieOthello(new JoueurOthello(), new JoueurOthello());
		
		p.jeu();
	}
	
}
