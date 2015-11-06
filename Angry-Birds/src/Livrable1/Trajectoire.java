package Livrable1;

import java.awt.Point;

import javax.swing.JFrame;

public class Trajectoire {

	private Point pt, p, p1, p2;
	private double t;

	public Trajectoire(Point p, Point p1, Point p2, double t) {
		this.p = p;
		this.p1 = p1;
		this.p2 = p2;
		this.t = t;
		pt = courbeBez(p, p1, p2, t);
	}

	public Trajectoire(Point p, double t, JFrame f) {
		this.p = p;
		this.t = t;
		pt = Ricochet(p, t, f);
	}
	
	public Trajectoire(Point p, double t){
		this.p = p;
		this.t = t;
		pt = LigneDroite(p, t);
	}

	public Point getPt() {
		return pt;
	}

	public void setPt(Point pt) {
		this.pt = pt;
	}

	public Point courbeBez(Point p, Point p1, Point p2, double t) {
		Point rep = new Point(0, 0);
		rep.x = (int) (((1 - t) * (1 - t)) * p.x + 2 * t * (1 - t) * p1.x + (t * t)
				* p2.x);
		rep.y = (int) (((1 - t) * (1 - t)) * p.y + 2 * t * (1 - t) * p1.y + (t * t)
				* p2.y);
		return rep;
	}

	public Point LigneDroite(Point p, double t) {
		Point rep = new Point(0, 0);
		rep.x = (int) (((p.x + t) * (1 + t) - 20) * 20);
		rep.y = 200;
		return rep;
	}

	public Point Ricochet(Point p, double t, JFrame f) {
		Point rep = new Point(0, 0);
		rep.x = (int) (((p.x + t) * (1 + t) - 20) * 20);
		int y = 600;
		if (t*y  < f.getHeight() - 50) {
			rep.y = (int) ((t) * y);
		} else if (t*y > 420.0000000000003 && t*y < 880.0000000000007){
				rep.y -= (int) ((t) * (y) - (y + 280));
		}
		else if(t*y >= 880.0000000000007)
			rep.y = (int) (((t) * y) - 866);
		return rep;
	}
}
