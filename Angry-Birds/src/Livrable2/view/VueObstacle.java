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

	public VueObstacle(ModelObstacle m, ControllerObstacle c) {
		super.model = m;
		this.model = m ;
		this.controller = c;
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

	public void paintComponent(Graphics g) {
		if (!touche)
			g.setColor(couleurPrincipale);
		else
			g.setColor(couleurSecondaire);
		if (model.getForme().equals("rond"))
			g.fillOval(model.getCo().getX(), model.getCo().getY(), model.SIZE,
					model.SIZE);
		else {
			g.fillRect(model.getCo().getX(), model.getCo().getY(), model.SIZE,
					model.SIZE);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
	
	}
}