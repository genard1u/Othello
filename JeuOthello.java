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
	
	public int evaluationEval0(Eval0 e1 , Eval0 e2){
		int res =0;
		PartieOthello p = new PartieOthello(new JoueurOthello("m1", false, Pion.NOIR),new JoueurOthello("m1", false, Pion.BLANC) );
		p.lancer(e1,e2);
		return res;
	}

	public static void main(String[] args) {
		JeuOthello jeu = new JeuOthello();
		
		jeu.lancer();
	}
	
}
