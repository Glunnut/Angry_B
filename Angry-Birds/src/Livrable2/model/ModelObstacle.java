package Livrable2.model;

import java.awt.Color;
import java.util.Random;

import Livrable2.ab.Coordonne;

public class ModelObstacle extends Model {

	/*-------------------------------ATTRIBUTS------------------------*/

	//Forme des  obstacles
	private boolean forme;
	
	//Création d'un random
	private Random r = new Random();
	
	//Taille des obstacles
	public final int SIZE = 25;
	
	//Coordonnee x et y des obstacles
	private int x, y;

	/*-------------------------------CONSTRUCTEURS------------------------*/

	/**
	 * Constructeur du modele de l'obstacle
	 * 
	 * @param x
	 * @param y
	 */
	public ModelObstacle(int x, int y) {
		this.x = x;
		this.y = y;
		co = new Coordonne(x, y);
		super.couleurPrincipale = Color.blue;
		super.couleurSecondaire = Color.red;
		this.forme = r.nextBoolean();
	}
	
	public ModelObstacle(int x, int y,boolean forme) {
		this.x = x;
		this.y = y;
		co = new Coordonne(x, y);
		super.couleurPrincipale = Color.blue;
		super.couleurSecondaire = Color.red;
		this.forme = forme;
	}
	

	/*-------------------------------GETTERS------------------------*/

	/**
	 * Renvoie la forme des obstacles
	 * @return
	 */
	public String getForme() {
		if (this.forme)
			return "rond";
		return "carre";
	}



	/**
	 * Renvoie la coordonnee x
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Renvoie la coordonne y
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/*-------------------------------SETTERS------------------------*/

	/**
	 * Modifie la valeur de x
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Modifie la valeur de y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}


}