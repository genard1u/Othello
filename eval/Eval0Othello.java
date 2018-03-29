package othello.eval;

import othello.Etat;
import othello.EtatOthello;

public abstract class  Eval0Othello extends Eval0 {

	public Eval0Othello() {
	}

	@Override
	public int eval(Etat etat) {
		// TODO Auto-generated method stub
		return eval0((EtatOthello) etat);
	}
	
	public abstract int eval0(EtatOthello etat);

}
