package Livrable2.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import Livrable2.ab.Coordonne;
import Livrable2.controller.ControllerObstacle;
import Livrable2.model.ModelObstacle;

public class VueObstacle extends Vue implements Observer {

	/*-------------------------------ATTRIBUTS------------------------*/

	// Couleurs des obstacles
	private Color couleurPrincipale, couleurSecondaire;

	// Creation d'un modele obstacle
	private ModelObstacle model;

	// Forme des obstacles
	private String forme;

	// Creation d'un rectangle
	private Rectangle rec;

	// Etat oiseau et obstacle
	public static boolean touche = false;

	// Coordonnee x et y d'un obstacle
	private int x, y;

	// Vie de l'obstacle
	private int vie = 100;
	
	/*-------------------------------CONSTRUCTEURS------------------------*/

	/**
	 * Constructeur de la vue des obstacles
	 * 
	 * @param m
	 * @param c
	 */
	public VueObstacle(ModelObstacle m, ControllerObstacle c) {
		super.model = m;
		this.model = m;
		this.controller = c;
		this.x = m.getX();
		this.y = m.getY();
		rec = new Rectangle(model.getX(), model.getY(), model.SIZE, model.SIZE);
		couleurPrincipale = model.getCouleurPrincipale();
		couleurSecondaire = model.getCouleurSecondaire();
		this.forme = model.getForme();
	}

	/*-------------------------------GETTERS------------------------*/

	/**
	 * Renvoie la forme de l'obstacle
	 * 
	 * @return
	 */
	public String getForme() {
		return forme;
	}

	/**
	 * Renvoie le rectangle
	 * 
	 * @return
	 */
	public Rectangle getRec() {
		return rec;
	}

	/**
	 * Renvoie la coordonnee x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Renvoie la coordonnee y
	 */
	public int getY() {
		return y;
	}

	/*-------------------------------SETTERS------------------------*/

	/**
	 * Modifie l'etat touche
	 * 
	 * @param touche
	 */
	public void setTouche(boolean touche) {
		this.touche = touche;
	}
	
	public boolean getTouche(){
		return this.touche;
	}

	/**
	 * Affiche l'obstacle sur la frame
	 */
	public void paintComponent(Graphics g) {
		if (!touche)
			g.setColor(couleurPrincipale);
		else {
			g.setColor(couleurSecondaire);
		}
		if (model.getForme().equals("rond")) {
			if (getVie() <= 50)
				g.drawImage(new ImageIcon("res/block2Destroy.png").getImage(), this.x, this.y, this);
			else
				g.drawImage(new ImageIcon("res/block2.png").getImage(), this.x, this.y, null);
		}
		// g.fillOval(this.x, this.y, model.SIZE,model.SIZE);
		else {
			if (getVie() <= 50)
				g.drawImage(new ImageIcon("res/block1Destroy.png").getImage(), this.x, this.y, null);
			else
				g.drawImage(new ImageIcon("res/block1.png").getImage(), this.x, this.y, null);
			// g.fillRect(this.x,this.y, model.SIZE,model.SIZE);
		}
	}

	/**
	 * Modifie la coordonnee x
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Modifie la coordonne y
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	public void setVie(int x) {
		this.vie -= x;
	}

	public int getVie() {
		return this.vie;
	}

	/*-------------------------------METHODES------------------------*/

	/**
	 * Update la vue
	 */
	@Override
	public void update(Observable o, Object arg) {

	}
}