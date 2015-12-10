package Livrable2.model;

import java.awt.Color;

import Livrable2.ab.Coordonne;

public class ModelOiseau extends Model {

	protected boolean lance = false;
	private double directionY = 0.0;
	int angle = 0;

	public ModelOiseau() {
		co = new Coordonne(120, 400);
		coInit = new Coordonne(co.x, co.y);
		taille = 30;
		couleurPrincipale = Color.RED;
		couleurSecondaire = new Color(250, 224, 173);
	}

	public boolean estLance() {
		return lance;
	}

	public double getDirectionY() {
		return directionY;
	}
	
	public int angle() {
		return angle;
	}

	public void setDirectionY(double directionY) {
		this.directionY = directionY;
	}

	public void setLance(boolean lance) {
		this.lance = lance;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}

}