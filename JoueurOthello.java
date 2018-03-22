/**
 * 
 */
package othello;

/**
 * @author collign57u
 */
public class JoueurOthello extends Joueur {

	private Pion p;
	
	
	public JoueurOthello(Pion p) {
		this.p = p;
	}
	
	public Pion getPion() {
		return p;
	}

}
