package othello.eval;

import othello.EtatOthello;
import othello.Pion;

public class Eval0Othello_2 extends Eval0Othello {

	public Eval0Othello_2() {}

	@Override
	public int eval0(EtatOthello etat) {
		return etat.valJeton(Pion.NOIR)-etat.valJeton(Pion.BLANC);
	}

}
