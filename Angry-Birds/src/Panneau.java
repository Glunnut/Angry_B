import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panneau extends JPanel {

	private Obstacle o1 = new Obstacle(20, 450);
	private Obstacle o2 = new Obstacle(140, 60);
	private Obstacle o3 = new Obstacle(800, 450);
	private Rectangle r;
	private int posX = 0;
	private int posY = 0;

	private ArrayList<Point> pts = new ArrayList<Point>();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillOval(posX, posY, 20, 20);
		g.setColor(Color.orange);
		r = new Rectangle(posX, posY, 20, 20);
		
		for (int i = 0; i < pts.size(); i += 2) {
			g.fillOval(pts.get(i).x + 2, pts.get(i).y + 3, 5, 5);
			g.fillArc(posX + 19, posY - 15, 25, 50, 160, 30);
		}

		pts.add(new Point(posX, posY));

		g.setColor(Color.blue);
		o1.afficher(g);
		o2.afficher(g);
		o3.afficher(g);

		if (r.intersectsLine(this.getWidth(), 0, this.getWidth(), this.getHeight())) {
			pts.removeAll(pts);
		}

	}
	
	public ArrayList<Point> getPts() {
		return pts;
	}

	public void setPts(ArrayList<Point> pts) {
		this.pts = pts;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = (int) posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
