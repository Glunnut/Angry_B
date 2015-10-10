import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panneau extends JPanel {

	private Point p1 = new Point(20, 450);
	private Point p2 = new Point(140, 60);
	private Point p3 = new Point(800, 450);
	private Obstacle o1 = new Obstacle(20, 450);
	private Obstacle o2 = new Obstacle(140,60);
	private Obstacle o3 = new Obstacle(800, 450);
	private int posX = 0;
	private int posY = 0;

	private ArrayList<Point> pts = new ArrayList<Point>();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillOval(posX, posY, 20, 20);
		g.setColor(Color.orange);
		g.fillArc(posX+19, posY - 15, 25, 50, 160, 30);
		
		for (int i = 0; i < pts.size(); i += 2) {
			g.fillOval(pts.get(i).x + 2, pts.get(i).y + 3, 5, 5);
		}
		
		pts.add(new Point(posX, posY));
	
		g.setColor(Color.blue);
		o1.afficher(g);
		o2.afficher(g);
		o3.afficher(g);
		
		if(pts.size()>100){
			pts.removeAll(pts);
		}
		/*g.fillOval(p1.x, p1.y, 20, 20);
		g.fillOval(p2.x, p2.y, 20, 20);
		g.fillOval(p3.x, p3.y, 20, 20);*/

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
