package othello;

public abstract class Joueur {
	
	protected String nom;
	protected int partiesGagnees;
	
	
	public Joueur(String joueur) {
		nom = joueur;
		partiesGagnees = 0;
	}

	public int partiesGagnees() {
		return partiesGagnees;
	}
	
	public String nomJoueur() {
		return nom;
	}
	
	public void victoire() {
		partiesGagnees ++;
	}
	
}
