package Livrable1;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Obstacle extends JPanel{

	private int posX;
	private int posY;
	private final int SIZE = 25;
	private static List<Obstacle> obstacles = new ArrayList<Obstacle>();

	public Obstacle(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		obstacles.add(this);
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
		g.setColor(Color.BLUE);
		for( Obstacle o  : obstacles ){
			g.fillOval(o.getPosX(), o.getPosY(), o.SIZE, o.SIZE);
		}
	}
	
}
