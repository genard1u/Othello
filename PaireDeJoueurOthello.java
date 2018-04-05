package othello;

public class PaireDeJoueurOthello extends PaireDeJoueurs {

	public PaireDeJoueurOthello(int nbJoueurMachine) {
		super(new JoueurOthello("1", false,Pion.NOIR),new JoueurOthello("2", false,Pion.BLANC));
		if (nbJoueurMachine <2){
			j2.setHumain(true);
			if (nbJoueurMachine == 0){
				j2.setHumain(true);
			}
		}
	}

}
