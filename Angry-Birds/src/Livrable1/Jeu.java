package Livrable1;

import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Jeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Panneau pan = new Panneau();
	private Point p1 = new Point(20, 350);
	private Point p2, p3;
	private Random r = new Random();
	private Timer timer;
	static int k = 0;
	static int x=900;
	static int y=500;
	private int nb = 0;
	private int i =0;

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
			timer = new Timer(true);
			timer.schedule(new TimerTask() {
				public void run() {
					go();
					timer.cancel();
				}
			}, 0, 5000);
		
	}

	public Point courbeBez(Point p, Point p1, Point p2, double t) {
		Point rep = new Point(0, 0);
		rep.x = (int) (((1 - t) * (1 - t)) * p.x + 2 * t * (1 - t) * p1.x + (t * t)
				* p2.x);
		rep.y = (int) (((1 - t) * (1 - t)) * p.y + 2 * t * (1 - t) * p1.y + (t * t)
				* p2.y);
		return rep;

	}
	public void reinit(){
		pan.setPosX(p1.x);
		pan.setPosY(p1.y);
		pan.repaint();
		
	}
	void go() {
		pan.repaint();
		k = 0;
		double t = 0;
		do {
			p2 = new Point(140, r.nextInt(this.getHeight()));
			p3 = new Point(this.getWidth(), r.nextInt(this.getHeight()));
		} while (p2.y > 250);
		
		while(k<100){
		//for (int k = 0; k < 100; k++) {
			
			t = t + 0.01;
			Point rep = new Point(courbeBez(p1, p2, p3, t));
			// System.out.println(rep.x);
			// System.out.println(rep.y);
			
			pan.setPosX(rep.x);

			pan.setPosY(rep.y);
			
			pan.repaint();
			
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(k == 100){
				reinit();
				restart();
			}else if(k == 101){
				reinit();
				restartt();
			}
			
		
		//}
		}
	}
	
	public void restart(){
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		
		if(i<nb) {
		go();
		}
	}
	
	public void restartt(){
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		
		if(i<nb) {
			go();
			}
	}
	
	
}
