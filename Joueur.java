package othello;

public abstract class Joueur {
	
	protected String nom;
	protected boolean estHumain;
	protected int partiesGagnees;
	
	
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

	public void setHumain(boolean b) {
		// TODO Auto-generated method stub
		estHumain = b ;
	}
	
}
