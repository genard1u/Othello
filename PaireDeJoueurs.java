package othello;

public class PaireDeJoueurs {

	protected Joueur j1;
	protected Joueur j2;
	
	protected Joueur joueurCourant;
	
	
	public PaireDeJoueurs(Joueur j1, Joueur j2) {
		assert j1.estHumain() != j2.estHumain();
		
		this.j1 = j1;
		this.j2 = j2;
	}
	
	public PaireDeJoueurs(String nom1, String nom2) {
		Joueur j1 = new Joueur(nom1, false);
	}
	
	public Joueur joueurHumain() {
		Joueur humain;
		
		if (j1.estHumain()) {
			humain = j1;
		}
		else {
			humain = j2;
		}
		
		return humain;
	}
	
	public Joueur joueurMachine() {
        Joueur machine;
		
		if (j1.estMachine()) {
			machine = j1;
		}
		else {
			machine = j2;
		}
		
		return machine;
	}
	
	public Joueur joueurCourant() {
		return joueurCourant;
	}
	
}
