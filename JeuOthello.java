package othello;

public class JeuOthello extends Jeu {

	public JeuOthello() {
		j1 = new JoueurOthello("j1");
		j2 = new JoueurOthello("j2");
	}
	
	@Override
	public boolean continuer() {
		return false;
	}
	
	@Override
	public void lancer() {
        while (continuer()) {
			partieCourante = new PartieOthello(j1, j2);
			partieCourante.lancer();
			afficherScore();
		}
	}

	public static void main(String[] args) {
		JeuOthello jeu = new JeuOthello();
		
		jeu.lancer();
	}
	
}
