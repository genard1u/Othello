package othello.eval;

import othello.Pion;
import othello.etat.EtatOthello;

public class Eval0Othello_1 extends Eval0Othello {

	public Eval0Othello_1() {}

	@Override
	public int eval0(EtatOthello etat) {
		return etat.nbJeton(Pion.NOIR)-etat.nbJeton(Pion.BLANC);
	}

}
