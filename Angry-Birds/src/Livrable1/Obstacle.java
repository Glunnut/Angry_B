package Livrable1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Obstacle extends JPanel {

	private int posX;
	private int posY;
	private final int SIZE = 25;
	public static List<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Rectangle rec;
	private Color colObs = Color.BLUE;

	public Color getColObs() {
		return colObs;
	}

	public void setColObs(Color colObs) {
		this.colObs = colObs;
	}

	public Obstacle(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		rec = new Rectangle(posX, posY, SIZE, SIZE);
		obstacles.add(this);
	}

	public Rectangle getRec() {
		return rec;
	}

	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	public int getDiametre() {
		return SIZE;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public static void afficher(Graphics g) {
		for (Obstacle o : obstacles) {
			g.setColor(o.getColObs());
			g.fillOval(o.getPosX(), o.getPosY(), o.SIZE, o.SIZE);
		}
	}

	public void enlever(Obstacle o) {
		obstacles.remove(o);
	}

}
