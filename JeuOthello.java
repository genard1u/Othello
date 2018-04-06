package othello;

import othello.eval.Eval0;

public class JeuOthello extends Jeu {

	public JeuOthello() {
		j1 = new JoueurOthello("j1");
		j2 = new JoueurOthello("j2");
	}
	
	@Override
	public boolean continuer() {
		return true;
	}
	
	@Override
	public void lancer() {
        while (continuer()) {
			partieCourante = new PartieOthello(j1, j2);
			Joueur gagnant = partieCourante.lancer();
			
			gagnant.victoire();
			afficherScore();
			
		}
	}
	
	public int evaluationEval0(Eval0 e1 , Eval0 e2 , int profondeur){
		int res =0;
		Joueur jo1 = new JoueurOthello("m1", false, Pion.NOIR);
		Joueur jo2 = new JoueurOthello("m2", false, Pion.BLANC);
		PartieOthello p = new PartieOthello(jo1,jo2 );
		for (int i = 0 ; i<profondeur; i++){
			
			Joueur j = p.lancer(e1,e2);
			if (j.nom == "m1" ){
				res++;
			}else{
				res--;
			}
			if(p.j1.nom == jo1.nom){
				p = new PartieOthello(jo2,jo1 );
			}else{
				p = new PartieOthello(jo1,jo2 );
			}
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
		
		jeu.lancer();
	}
	
}
