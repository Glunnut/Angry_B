package Livrable1;

import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Jeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Panneau pan = new Panneau();
	private Point p1 = new Point(20, 350), p2, p3;
	private Random r = new Random();
	static int x = 900, y = 500;
	private int nb = 0, i = 0;
	static boolean touche = false, sorti = false;
	private Trajectoire traj;
	
	public Jeu(String title) {
		super(title);
	}

	public void configFrame() {
		this.setSize(x, y);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.setVisible(true);
	}

	public void lancerJeu(int nbLancer) {
		this.nb = nbLancer;
		go();
	}

	public void reinit() {
		pan.setPosX(p1.x);
		pan.setPosY(p1.y);
		pan.repaint();
		touche = false;
		sorti = false;
	}

	void go() {
		pan.repaint();
		double t = 0;
		do {
			p2 = new Point(140, r.nextInt(this.getHeight()));
			p3 = new Point(this.getWidth(), r.nextInt(this.getHeight()));
		} while (p2.y > 250);

		while ( !touche || !sorti ) {

			t = t + 0.01;
			traj = new Trajectoire(p1, p2, p3, t);
			Point rep = new Point(traj.getPt());

			pan.setPosX(rep.x);
			pan.setPosY(rep.y);
			pan.repaint();

			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (touche || sorti) {
				reinit();
				restart();
			}

		}
	}

	public void restart() {
		try {
			if (touche) {
				Thread.sleep(2000);
			} else {
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		if (i < nb) {
			go();
		}
	}

}
