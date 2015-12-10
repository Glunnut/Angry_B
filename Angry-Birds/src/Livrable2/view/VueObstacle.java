package Livrable2.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import Livrable2.ab.Coordonne;
import Livrable2.controller.ControllerObstacle;
import Livrable2.model.ModelObstacle;

public class VueObstacle extends Vue implements Observer {

	private Color couleurPrincipale,couleurSecondaire;
	private ModelObstacle model;
	private String forme;
	private Rectangle rec;
	public static boolean touche = false;
	private int x,y;
	public VueObstacle(ModelObstacle m, ControllerObstacle c) {
		super.model = m;
		this.model = m ;
		this.controller = c;
		this.x = m.getX();
		this.y = m.getY();
		rec = new Rectangle(model.getX(), model.getY(), model.SIZE, model.SIZE);
		couleurPrincipale = model.getCouleurPrincipale();
		couleurSecondaire = model.getCouleurSecondaire();
		this.forme = model.getForme();
	}

	public String getForme() {
		return forme;
	}

	public Rectangle getRec() {
		return rec;
	}
	public void setTouche(boolean touche){
		this.touche = touche;
	}
	public void paintComponent(Graphics g) {
		if (!touche)
			g.setColor(couleurPrincipale);
		else{
			g.setColor(couleurSecondaire);
		}
		if (model.getForme().equals("rond"))
			g.fillOval(this.x, this.y, model.SIZE,
					model.SIZE);
		else {
			g.fillRect(this.x,this.y, model.SIZE,
					model.SIZE);
		}
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public void update(Observable o, Object arg) {
	
	}
}