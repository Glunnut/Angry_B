package Livrable2.model;

import java.awt.Color;
import java.util.Random;

import Livrable2.ab.Coordonne;

public class ModelObstacle extends Model {

	/*-------------------------------ATTRIBUTS------------------------*/
	
	private boolean forme;
	private Random r = new Random();
	public final int SIZE = 25;
	private int x, y;

	
	/*-------------------------------CONSTRUCTEURS------------------------*/
	
	public ModelObstacle(int x, int y) {
		this.x = x;
		this.y = y;
		co = new Coordonne(x, y);
		super.couleurPrincipale = Color.blue;
		super.couleurSecondaire = Color.red;
		this.forme = r.nextBoolean();
	}
	
	/*-------------------------------GETTERS------------------------*/

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	/*-------------------------------SETTERS------------------------*/
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	/*-------------------------------METHODES------------------------*/
	
	public String getForme() {
		if (this.forme)
			return "rond";
		return "rectangle";
	}
}