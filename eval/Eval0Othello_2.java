package othello.eval;

import othello.Pion;
import othello.etat.EtatOthello;

public class Eval0Othello_2 extends Eval0Othello {

	public Eval0Othello_2() {}

	@Override
	public int eval0(EtatOthello etat) {
		return -1;//etat.valJeton(Pion.NOIR)-etat.valJeton(Pion.BLANC);
	}

}
