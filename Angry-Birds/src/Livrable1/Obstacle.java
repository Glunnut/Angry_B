package Livrable1;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Obstacle extends JPanel{

	private int posX;
	private int posY;
	private final int size = 25;

	public Obstacle(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getDiametre() {
		return size;
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

	public void afficher(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(posX, posY, size	, size);
	}
	
}
