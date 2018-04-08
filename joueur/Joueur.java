package othello.joueur;

public abstract class Joueur {
	
	protected String nom;
	protected int partiesGagnees;
	protected boolean estHumain;
	
	
	public Joueur(String joueur) {
		nom = joueur;
		partiesGagnees = 0;
	}

	public Joueur(String joueur, boolean humain) {
		this(joueur);
		estHumain = humain;
	}
	
	public boolean estHumain() {
		return estHumain;
	}
	
	public boolean estMachine() {
		return !estHumain;
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

	public void setHumain(boolean humainOuMachine) {
		estHumain = humainOuMachine;
	}
	
}
