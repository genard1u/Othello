package othello;

import othello.eval.Eval0;
import othello.eval.Eval0Othello_1;
import othello.eval.Eval0Othello_2;
import othello.eval.Eval0Othello_3;

public class JeuOthello extends Jeu {

	public JeuOthello() {
		j1 = new JoueurOthello("j1",false,Pion.NOIR);
		j2 = new JoueurOthello("j2",false,Pion.BLANC);
	}
	
	@Override
	public boolean continuer() {
		return true;
	}
	
	@Override
	public void lancer() {
		int i =0;
        while (continuer()&& i!=5) {
			partieCourante = new PartieOthello(j1, j2);
			Joueur gagnant = partieCourante.lancer(1);
			
			gagnant.victoire();
			afficherScore();
			i++;
			
		}
	}
	
	public int evaluationEval0(Eval0 e1 , Eval0 e2 , int profondeur){
		int res =0;
		JoueurOthello jo1 = new JoueurOthello("m1", false, Pion.NOIR);
		JoueurOthello jo2 = new JoueurOthello("m2", false, Pion.BLANC);
		PartieOthello p = new PartieOthello(jo1,jo2 );
		Joueur j = p.lancer(profondeur, e1,e2);
		 
		if (((JoueurOthello)j).couleur() == Pion.NOIR ){
			res++;
		}else{
			res--;
		}
		/*((JoueurOthello)(p.j1)).setPion(Pion.BLANC);
		((JoueurOthello)(p.j2)).setPion(Pion.NOIR);*/
		p = new PartieOthello(jo2,jo1 );
		System.out.println(((JoueurOthello)(p.j1)).couleur());
		j = p.lancer(profondeur, e1,e2);
		 
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
		jeu.lancer();
		
	}
	
}
