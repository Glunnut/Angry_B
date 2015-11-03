package Livrable1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	private Obstacle o1, o2, o3, o4, o5, o6, o7, o8;
	private int posX = 0;
	private int posY = 0;
	private Oiseau oiseau = new Oiseau(posX, posY);
	private Timer timer = new Timer();
	private ScheduledExecutorService exec = Executors
			.newSingleThreadScheduledExecutor();

	private ArrayList<Point> pts = new ArrayList<Point>();
	private Runnable task = new Runnable() {
		@Override
		public void run() {
		}
	};

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
		g.setColor(Color.orange);
		for (int i = 0; i < pts.size(); i += 2) {
			g.fillOval(pts.get(i).x + 5, pts.get(i).y + 8, 5, 5);
		}
		for (final Obstacle o : Obstacle.obstacles) {
			if (oiseau.getRect().intersects(o.getRec())) {
				o.setColObs(Color.RED);
				Jeu.k = 100;
				pts.removeAll(pts);

				Timer timer = new Timer();

				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						o.setColObs(Color.BLUE);

					}
				}, 0, 500);
			}
		}

		pts.add(new Point(posX, posY));

		g.setColor(Color.blue);
		Obstacle.afficher(g);
		oiseau.afficher(g);
		oiseau.move(posX, posY);

		/*
		 * if (oiseau.getRect().intersectsLine(this.getWidth(), 0,
		 * this.getWidth(), this.getHeight())) { Jeu.k = 101;
		 * pts.removeAll(pts); }
		 */
		if (oiseau.getRect().getX() > Jeu.x + 5
				|| oiseau.getRect().getY() > Jeu.y + 5
				|| oiseau.getRect().getY() < 0 || oiseau.getRect().getX() < 0) {
			Jeu.k = 101;
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
