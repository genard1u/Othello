package othello;

public class JeuOthello extends Jeu {

	public JeuOthello() {
		j1 = new JoueurOthello();
		j2 = new JoueurOthello();
	}
	
	@Override
	public boolean continuer() {
		return false;
	}
	
	@Override
	public void lancer() {
        while (continuer()) {
			
		}
	}

	public static void main(String[] args) {
		JeuOthello jeu = new JeuOthello();
		
		jeu.lancer();
	}
	
}
