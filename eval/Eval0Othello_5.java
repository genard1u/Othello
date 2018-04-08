package othello.eval;

import othello.etat.Etat;

public class Eval0Othello_5 extends Eval0 {
	
	public Eval0Othello_5() {
		super();
	}

	@Override
	public int eval(Etat etat) {
		return etat.eval05();
	}

}
