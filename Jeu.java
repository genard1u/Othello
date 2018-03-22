package othello;

public abstract class Jeu {

	protected Joueur j1;
	protected Joueur j2;
	
	protected Partie partieCourante;
	
	
	public abstract boolean continuer();
	public abstract void lancer();
	
	public String recupererScore() {
		StringBuilder score = new StringBuilder();
		
		score.append("Joueur j1 : ");
		score.append(j1.partiesGagnees());
		score.append("\t");
		score.append("Joueur j2 : ");
		score.append(j2.partiesGagnees());
		
		return score.toString();
	}
	
}
