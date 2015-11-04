package Livrable1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Oiseau{
	
	//Creation d'un objet rectangle
	private Rectangle rect;
	
	/**Attribut d'un Oiseau
	 *x - Coordonnee x de l'oiseau
	 *y - Coordonnee y de l'oiseau
	 *height - hauteur de l'oiseau
	 *width - largeur de l'oiseau
	 */
	private int x = 0;
	private int y = 0;
	private int height = 30;
	private int width = 30;

	/**
	 * Constructeur
	 * @param x
	 * @param y
	 */
	public Oiseau(int x, int y) {
		this.x = x;
		this.y = y;
		rect = new Rectangle(x, y, width, height);
	}

	//---------------------GETTERS--------------------------//
	
	public Rectangle getRect() {
		return this.rect;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}




	//---------------------SETTERS--------------------------//

	public void setRect(Rectangle r) {
		this.rect = r;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Deplacement d'un oiseau
	 * @param x
	 * @param y
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		this.rect.setBounds(x, y, rect.width, rect.height);
	}

	/**
	 * Affichage d'un Oiseau
	 * @param g
	 */
	public void afficher(Graphics g) {
		g.drawOval(x, y, height, width);
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, height, width);
		g.setColor(Color.YELLOW);
		g.fillArc(x + 30, y - 1, width, height, 160, 30);
		g.setColor(Color.BLACK);
		g.drawOval(x + 8, y + 10, width - 10, height - 20);
		g.setColor(Color.WHITE);
		g.fillOval(x + 8, y + 10, width - 10, height - 20);
		g.setColor(Color.BLACK);
		g.fillOval(x + 13, y + 10, width - 20, height - 20);
	}
	


	
}
