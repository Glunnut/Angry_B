package Livrable1;
import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Jeu extends JFrame {

	private Panneau pan = new Panneau();
	private Point p1 = new Point(20, 350);
	private Point p2,p3;
	private Random r = new Random();
	private Timer timer;
	private Timer timer2;

	public Jeu(String title) {
		super(title);
		

		
	}
	
	public void configFrame(){
		this.setSize(900, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.setVisible(true);
	}
	
	public void lancerJeu(int nbLancer){
		for (int i = 0; i < nbLancer; i++) {
			timer = new Timer(true);
			timer.scheduleAtFixedRate(
			    new TimerTask() {
			      public void run() { go(); }
			    }, 0, 5000);
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
			p3 = new Point(this.getWidth(), r.nextInt(this.getHeight()));
		} while (p2.y > 250);
		for (int i = 0; i < 100; i++) {
			t = t + 0.01;
			Point rep = new Point(courbeBez(p1, p2, p3, t));
			System.out.println(rep.x);
			System.out.println(rep.y);
			pan.setPosX(rep.x);

			pan.setPosY(rep.y);
	    	  pan.repaint();

			

			timer2 = new Timer(true);
			timer2.scheduleAtFixedRate(
			    new TimerTask() {
			      public void run() { 
			    	  System.out.println("ok");
			      }
			    }, 0, 40);

		}
	}
}