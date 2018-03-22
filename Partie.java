package othello;

public abstract class Partie {

	protected Etat etat;
	
	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	
	
	protected Partie() {}
	
	public Partie(Joueur un, Joueur deux) {
		j1 = un;
		j2 = deux;
	}
	
	protected abstract boolean estTerminee();
}
