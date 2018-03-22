package othello;

public abstract class Joueur {
	
	protected int partiesGagnees;
	
	
	public Joueur() {
		partiesGagnees = 0;
	}

	public int partiesGagnees() {
		return partiesGagnees;
	}
	
}
