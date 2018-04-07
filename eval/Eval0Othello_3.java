package othello.eval;

import othello.EtatOthello;
import othello.Pion;

public class Eval0Othello_3 extends Eval0Othello {

	public Eval0Othello_3() {}

	@Override
	public int eval0(EtatOthello etat) {
		return (etat.valJeton(Pion.NOIR)+etat.nbJeton(Pion.NOIR))-(etat.valJeton(Pion.BLANC)+etat.nbJeton(Pion.BLANC));
	}

}
