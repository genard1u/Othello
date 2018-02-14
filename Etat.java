/**
 * 
 */
package othello;

import java.util.ArrayList;

/**
 * @author Collignon Valentin
 * @author Genard Pierre
 *
 */
public abstract class Etat {

	/**
	 * 
	 */
	public Etat() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract ArrayList<Etat> successeurs(Joueur j);
	
	public boolean equals(Etat e){
		return this.toString().equals(e.toString());
	}
	
	

}
