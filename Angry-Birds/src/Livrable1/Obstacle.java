package Livrable1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacle {

	//Creation d'un objet rectangle
	private Rectangle rec;

	/**Attribut d'un Obstacle
	 * posX - Coordonnee x de l'obstacle
	 * posY - Coordonnee y de l'obstacle
	 * SIZE - diamètre de l'obstacle
	 */
	private int posX;
	private int posY;
	private final int SIZE = 25;
	
	//Creation d'une liste d'obstacle
	public static List<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	//Attribution d'une couleur aux obstacles
	private Color colObs = Color.BLUE;
	private boolean rond;
	Random r = new Random();
	/**
	 * Constructeur
	 * @param posX
	 * @param posY
	 */
	public Obstacle(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.rond = r.nextBoolean();
		rec = new Rectangle(posX, posY, SIZE, SIZE);
		obstacles.add(this);
	}

	//---------------------GETTERS--------------------------//
	
	public Rectangle getRec() {
		return rec;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getDiametre() {
		return SIZE;
	}
	
	public Color getColObs() {
		return colObs;
	}
	
	public String getForme(){
		if(this.rond)
			return "rond";
		return "rectangle";
	}
	
	//---------------------SETTERS--------------------------//

	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setColObs(Color colObs) {
		this.colObs = colObs;
	}
	
	/**
	 * Enlevement d'un obstacle de la liste des obstacles
	 * @param o
	 */
	 
	public void enlever(Obstacle o) {
		obstacles.remove(o);
	}
	
	/**
	 * Affichage d'un obstacle 
	 * @param g
	 */
	public static void afficher(Graphics g) {
		for (Obstacle o : obstacles) {
			g.setColor(o.getColObs());
			if(o.getForme().equals("rond"))
				g.fillOval(o.getPosX(), o.getPosY(), o.SIZE, o.SIZE);
			else{
				g.fillRect(o.getPosX(), o.getPosY(), o.SIZE, o.SIZE);
			}
		}
	}

	

}
