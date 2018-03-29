package othello;

public abstract class Partie {

	protected Etat etat;
	
	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	
	
	public Partie() {}
	
	public Partie(Joueur un, Joueur deux) {
		j1 = un;
		j2 = deux;
	}
	
	protected abstract void allerSurUnSuccesseur();
	protected abstract void aucunSuccesseur();
	protected abstract boolean estTerminee();
	
	public Joueur lancer() {
		while (!estTerminee()) {
			tour();
		}
		
		return getGagnant();
	}
	
	protected abstract void tour();
	protected abstract Joueur getGagnant();
	
}
