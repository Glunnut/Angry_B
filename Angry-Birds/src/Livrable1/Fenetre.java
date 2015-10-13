package Livrable1;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

	private Panneau pan = new Panneau();
	private Point p1 = new Point(20, 350);
	private Point p2;
	private Point p3;
	Random r = new Random();

	public Fenetre() {

		this.setTitle("Animation");

		this.setSize(900, 500);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);

		this.setContentPane(pan);

		this.setVisible(true);
		for (int i = 0; i < 10; i++) {
			go();
		}
	}

	public Point courbeBez(Point p, Point p1, Point p2, double t) {
		Point rep = new Point(0, 0);
		rep.x = (int) (((1 - t) * (1 - t)) * p.x + 2 * t * (1 - t) * p1.x + (t * t)
				* p2.x);
		rep.y = (int) (((1 - t) * (1 - t)) * p.y + 2 * t * (1 - t) * p1.y + (t * t)
				* p2.y);
		return rep;

	}

	void go() {
		double t = 0;
		do {
			p2 = new Point(140, r.nextInt(this.getHeight()));
			p3 = new Point(900, r.nextInt(this.getHeight()));
		} while (p2.y > 250);
		for (int i = 0; i < 100; i++) {
			t = t + 0.01;
			Point rep = new Point(courbeBez(p1, p2, p3, t));
			System.out.println(rep.x);
			System.out.println(rep.y);
			pan.setPosX(rep.x);

			pan.setPosY(rep.y);

			pan.repaint();

			try {

				Thread.sleep(40);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}
	}
}
