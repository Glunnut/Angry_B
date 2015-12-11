package Livrable2.ab;

public class Coordonne {

	/*-------------------------------ATTRIBUTS------------------------*/

	// Coordonnee x
	 public int x;

	// Coordonnee y
	 public int y;

	/*-------------------------------CONSTRUCTEURS------------------------*/
	 
	/**
	 * Constructeur Coordonnee
	 * 
	 * @param x
	 * @param y
	 */
	public Coordonne(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*-------------------------------GETTERS------------------------*/
	
	/**
	 * Renvoi de la valeur de la coordonne x
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Renvoi de la valeur de la coordonne y
	 * @return
	 */
	public int getY() {
		return y;
	}

	/*-------------------------------SETTERS------------------------*/
	
	/**
	 * Changement de la valeur de la coordonne x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Changement de la valeur de la coordonne y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/*-------------------------------METHODES------------------------*/

	/**
	 * Ajout de Coordonnee
	 * 
	 * @param coord
	 */
	public void addCoord(Coordonne coord) {
		this.x = this.x + coord.getX();
		this.y = this.y + coord.getY();
	}

	/**
	 * Comparaison de Coordonnee
	 * 
	 * @param coord
	 * @return
	 */
	public boolean compare(Coordonne coord) {
		return ((this.x == coord.getX()) && (this.y == coord.getY()));
	}

	/**
	 * Methode d'affichage des coordonnees
	 */
	public String toString() {
		return "Coord : [" + x + " ; " + y + "]";
	}

}