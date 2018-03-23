/**
 * 
 */
package othello;

/**
 * @author collign57u
 */
public class JoueurOthello extends Joueur {

	private Pion p;
	
	
	public JoueurOthello() {
		p = null;
	}
	
	public JoueurOthello(Pion p) {
		this.p = p;
	}
	
	public Pion getPion() {
		return p;
	}

	public void setPion(Pion p) {
		this.p = p;
	}
	
	public String toString(){
		return "le  joueur avec le pion "+p; 
	}
	
}
