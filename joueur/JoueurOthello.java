package othello.joueur;

import othello.Pion;

/**
 * @author collign57u
 * @author Génard Pierre
 */
public class JoueurOthello extends Joueur {

	private Pion pion;
	
	
	public JoueurOthello(String nom) {
		super(nom);
		pion = null;
	}
	
	public JoueurOthello(String nom, Pion p) {
		super(nom);
		pion = p;
		
		assert p != Pion.RIEN;
	}
	
	public JoueurOthello(String nom, boolean humain, Pion p) {
		super(nom, humain);
		pion = p;
	}
	
	public Pion couleur() {
		return pion;
	}
	
	public void setCouleur(Pion p) {
		pion = p;
		
		assert p != Pion.RIEN;
	}
	
	public String toString() {
		return "Le joueur " + nom + " a les pions " + pion; 
	}
	
}
