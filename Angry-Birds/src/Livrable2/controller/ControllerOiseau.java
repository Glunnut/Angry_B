package Livrable2.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import Livrable2.ab.Jeu;
import Livrable2.model.ModelOiseau;
import Livrable2.view.VueOiseau;

public class ControllerOiseau extends Controller {
	private Jeu j;
	private boolean isDrag;
	private Point init;

	public ControllerOiseau(ModelOiseau modelOiseau) {
		this.j = modelOiseau.getJeu();
		this.isDrag = true;
	}
	public void setDrag(boolean b){
		isDrag = b;
	}
	public boolean isDrag(){
		return this.isDrag;
	}
	public Point getInit(){
		return this.init;
	}
	
	public double setAngleDep(int x,int y){
		double angle = Math.atan2(y-320,x-120);
		return angle+Math.PI;
	}
	public double setAngleDep(int x,int y,int x1,int y1){
		double angle = Math.atan2(y-y1,x-x1);
		return angle;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	
	static public double sqr(double a) {
        return a*a;
    }
 
    static public double Distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
    } 
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		isDrag = false;
		VueOiseau o = j.getOiseau();
		ModelOiseau mO = j.getModel();
		int x = o.getX();
		int y = o.getY();
		mO.setVitesse(Distance(x,y,120,320));
		System.out.println(Distance(x,y,120,320));
		System.out.println(y);
		init = new Point(x,y);
		mO.setAngleDep(setAngleDep(x,y));
		if (x < 20)
			x = 20;
		if (y > 410)
			y = 410;
		j.setP1(new Point(x, y));
		j.setGo(true);
		
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isDrag) {
			VueOiseau o = j.getOiseau();
			o.move(e.getX(), e.getY());
			if (e.getX() < 20)
				o.move(20, o.getY());
			if (e.getX() > 120)
				o.move(120, o.getY());
			if (e.getY() > 410)
				o.move(o.getX(), 410);
			if (e.getY() < 320)
				o.move(o.getX(), 320);
			j.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}