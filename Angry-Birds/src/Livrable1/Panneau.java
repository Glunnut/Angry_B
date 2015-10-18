package Livrable1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	private Obstacle o1, o2, o3, o4, o5, o6, o7, o8;
	private int posX = 0;
	private int posY = 0;
	private Oiseau oiseau = new Oiseau(posX, posY);

	private ArrayList<Point> pts = new ArrayList<Point>();

	public Panneau() {
		creationOsbtacles();
	}

	public void creationOsbtacles() {
		o1 = new Obstacle(750, 200);
		o2 = new Obstacle(770, 60);
		o3 = new Obstacle(800, 450);
		o4 = new Obstacle(770, 300);
		o5 = new Obstacle(790, 154);
		o6 = new Obstacle(830, 250);
		o7 = new Obstacle(820, 385);
		o8 = new Obstacle(825, 100);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(new ImageIcon("res/bg_menu.png").getImage(), 0, 0, null);
		// g.setColor(Color.red);
		// g.fillOval(posX, posY, 20, 20);
		g.setColor(Color.orange);
		g.fillArc(posX + 19, posY - 15, 25, 50, 160, 30);
		for (int i = 0; i < pts.size(); i += 2) {
			g.fillOval(pts.get(i).x + 2, pts.get(i).y + 3, 5, 5);
		}
		for (final Obstacle o : Obstacle.obstacles) {
			if (oiseau.getRect().intersects(o.getRec())) {
				o.setColObs(Color.RED);
				Timer timer = new Timer();

				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						o.setColObs(Color.BLUE);
					}
				}, 100);

			}
		}
		pts.add(new Point(posX, posY));

		g.setColor(Color.blue);
		Obstacle.afficher(g);
		oiseau.afficher(g);
		oiseau.move(posX, posY);
		System.out.println(oiseau.getX());

		if (oiseau.getRect().intersectsLine(this.getWidth(), 0, this.getWidth(),
				this.getHeight())) {
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
