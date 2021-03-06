package othello.jeu;

import othello.joueur.Joueur;
import othello.partie.Partie;

public abstract class Jeu {

	protected Joueur j1;
	protected Joueur j2;
	
	protected Partie partieCourante;
	
	
	protected abstract boolean continuer();
	public abstract void lancer(boolean affichage);
	
	public String score() {
		StringBuilder score = new StringBuilder(30);
		
		score.append("Joueur " + j1.nomJoueur());
		score.append(" : ");
		score.append(j1.partiesGagnees());
		score.append("\t");
		score.append("Joueur " + j2.nomJoueur());
		score.append(" : ");
		score.append(j2.partiesGagnees());
		
		return score.toString();
	} 
	
	public void afficherScore() {
		System.out.println(score());
	} 
	
}
