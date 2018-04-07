package othello.eval;

import othello.etat.Etat;
import othello.etat.EtatOthello;

public abstract class  Eval0Othello extends Eval0 {

	public static final int NB_EVAL0 = 4;
	
	
	public Eval0Othello() {}

	@Override
	public int eval(Etat etat) {
		return eval0((EtatOthello) etat);
	}
	
	public abstract int eval0(EtatOthello etat);

}
