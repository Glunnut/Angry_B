package Livrable1;

import java.awt.Point;

public class Trajectoire {

	private Point pt,p,p1,p2;
	private double t;
	
	public Trajectoire(Point p, Point p1, Point p2, double t) {
		this.p = p;
		this.p1 = p1;
		this.p2 = p2;
		this.t = t;
		pt = courbeBez(p, p1, p2, t);
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
}
