package othello;

public abstract class Jeu {

	protected Joueur j1;
	protected Joueur j2;
	
	protected Partie partieCourante;
	
	
	protected abstract boolean continuer();
	public abstract void lancer( boolean af);
	
	protected String score() {
		StringBuilder score = new StringBuilder();
		
		score.append("Joueur " + j1.nomJoueur());
		score.append(" : ");
		score.append(j1.partiesGagnees());
		score.append("\t");
		score.append("Joueur " + j2.nomJoueur());
		score.append(" : ");
		score.append(j2.partiesGagnees());
		
		return score.toString();
	}
	
	protected void afficherScore() {
		System.out.println(score());
	}
	
}
