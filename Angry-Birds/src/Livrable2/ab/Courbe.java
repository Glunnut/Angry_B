package Livrable2.ab;

import java.awt.Point;

import Livrable2.controller.Controller;
import Livrable2.controller.ControllerOiseau;
import Livrable2.model.Model;
import Livrable2.model.ModelOiseau;
import Livrable2.view.VueOiseau;

public class Courbe {
	/*-------------------------------ATTRIBUTS------------------------*/
	
	//Creation des points
	private Point pt;
	
	//Instant t
	private double t;
	
	private Jeu j;
	
	public ModelOiseau mO;
	private ControllerOiseau cO;
	
	private double vitesse;
	

	/*-------------------------------CONSTRUCTEURS------------------------*/
	
	/**
	 * Constructeur Courbe
	 * @param p
	 * @param p1
	 * @param p2
	 * @param t
	 */
	public Courbe(ModelOiseau m, double t) {
		this.mO=m;
		this.j=m.getJeu();
		this.t=t;
		pt = courbePhysique(t,mO.getAngleDep());
	}

	/*-------------------------------GETTERS------------------------*/
	
	/**
	 * Renvoi d'un point
	 * @return
	 */
	public Point getPt() {
		return pt;
	}

	/*-------------------------------METHODES------------------------*/
	
	/**
	 * Creation de la courbe de bezier
	 * @param p
	 * @param p1
	 * @param p2
	 * @param t
	 * @return
	 */
	public Point courbeBez(Point p, Point p1, Point p2, double t) {
		Point rep = new Point(0, 0);
		rep.x = (int) (((1 - t) * (1 - t)) * p.x + 2 * t * (1 - t) * p1.x + (t * t)
				* p2.x);
		rep.y = (int) (((1 - t) * (1 - t)) * p.y + 2 * t * (1 - t) * p1.y + (t * t)
				* p2.y);
		return rep;
	}
	
	
	
	public Point courbePhysique(double t, double angle){
		Point p = new Point(0,0);
		VueOiseau o = j.getOiseau();
		vitesse = mO.getVitesse();
		p.x = (int) (vitesse* Math.cos(angle) *t)+ o.getX();
		p.y = (int) (((90) *Math.pow(t,2))+ vitesse * Math.sin(angle)*t + o.getY());
		return p;
		
	}

}