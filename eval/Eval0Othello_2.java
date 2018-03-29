package othello.eval;

import othello.EtatOthello;
import othello.Pion;

public class Eval0Othello_2 extends Eval0Othello {

	public Eval0Othello_2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int eval0(EtatOthello etat) {
		// TODO Auto-generated method stub
		return etat.valJeton(Pion.NOIR)-etat.valJeton(Pion.BLANC);
	}

}
