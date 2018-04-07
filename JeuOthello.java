package othello;

import java.util.Scanner;

import othello.eval.Eval0;
import othello.eval.Eval0Othello_1;
import othello.eval.Eval0Othello_2;
import othello.eval.Eval0Othello_3;
import othello.eval.Eval0Othello_4;

public class JeuOthello extends Jeu {

	public JeuOthello() {
		j1 = new JoueurOthello("j1",false,Pion.NOIR);
		j2 = new JoueurOthello("j2",false,Pion.BLANC);
		boolean b;

		Scanner sc = new Scanner(System.in);
		System.out.println("voulez vous jouer(0) ou faire un evaluation d'eval0(1)?");
		b = choixBoolean(sc);
		if(b){
			System.out.println("Entrez le numero de deux fonction eval0 (il y en a 4)");
			choixEval0(sc);
		}else{
			System.out.println("voulez vous jouer contre un ami(0), une machine(1), ou voir une partie entre deux machine(2) ?");
			choixJoueur(sc);
			
		}
	}
	
	

	private void choixEval0(Scanner sc) {
		Eval0 e1 , e2;
		System.out.println("premier eval0");
		e1 = selectionEval0(sc);
		System.out.println("deuxieme eval0");
		e2 = selectionEval0(sc);
		System.out.println("entrez une profondeur ");
		int i = sc.nextInt();
		System.out.println(evaluationEval0(e1, e2, i));
		
	}



	private Eval0 selectionEval0(Scanner sc) {
		Eval0 e = new Eval0Othello_1();
		int i = sc.nextInt();
		while (i<=0||i>4){
			System.out.println("numero invalide encore un svp ");
			i= sc.nextInt();
		}
		switch(i){
			case 1:
				e = new Eval0Othello_1();
				break;
			case 2:
				e = new Eval0Othello_2();
				break;
			case 3:
				e = new Eval0Othello_3();
				break;
			case 4:
				e = new Eval0Othello_4();
				break;
		}
		return e;
	}



	private void choixJoueur(Scanner sc) {
		int i = sc.nextInt();
		switch (i){
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



	private boolean choixBoolean(Scanner sc) {
		if ( sc.nextInt()==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean continuer() {
		System.out.println("fini,voulez vous recomencer? oui(1) non(sinon)");
		return choixBoolean(new Scanner(System.in));
	}
	
	@Override
	public void lancer(boolean aff) {
        
		do{
			partieCourante = new PartieOthello(j1, j2);
			Joueur gagnant = partieCourante.lancer(1,aff);
		
			gagnant.victoire();
			afficherScore(); 
			
			
		}while (continuer());
	}
	
	public int evaluationEval0(Eval0 e1 , Eval0 e2 , int profondeur){
		int res =0;
		JoueurOthello jo1 = new JoueurOthello("m1", false, Pion.NOIR);
		JoueurOthello jo2 = new JoueurOthello("m2", false, Pion.BLANC);
		PartieOthello p = new PartieOthello(jo1,jo2 );
		Joueur j = p.lancer(profondeur,false,e1,e2);
		 
		if (((JoueurOthello)j).couleur() == Pion.NOIR ){
			System.out.println("11");
			res++;
		}else{
			System.out.println("-11");
			res--;
		}
		/*((JoueurOthello)(p.j1)).setPion(Pion.BLANC);
		((JoueurOthello)(p.j2)).setPion(Pion.NOIR);*/
		 p = new PartieOthello(jo1,jo2 );
		 
		j = p.lancer(profondeur,false, e2,e1);
		 
		if (((JoueurOthello)j).couleur() == Pion.NOIR ){
			res--;
		}else{
			res++;
		}
		
		
		if (res>1){
			res=1;
		}
		if (res<-1){
			res=-1;
		}
		return res;
	}
	
	

	public static void main(String[] args) {
		
		
		JeuOthello jeu = new JeuOthello();
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
