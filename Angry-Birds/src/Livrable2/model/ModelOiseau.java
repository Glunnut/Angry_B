package Livrable2.model;

import java.awt.Color;

import Livrable2.ab.Coordonne;
import Livrable2.ab.Jeu;

public class ModelOiseau extends Model {
	
	/*-------------------------------ATTRIBUTS------------------------*/
	
	//Etat de l'oiseau avant le debut de la partie
	protected boolean lance = false;
	
	//Direction dans laquelle l'oiseau se dirige
	private double directionY = 0.0;
	
	//Angle de vol
	int angle = 0;
	
	private double angleDep=0;
	
	//Le jeu
	private Jeu j;
	
	private double vitesse=0.0;
	/*-------------------------------CONSTRUCTEURS------------------------*/

	/**
	 * Constructeur du modele de l'oiseau
	 * @param j
	 */
	public ModelOiseau(Jeu j) {
		this.j = j;
		co = new Coordonne(120, 399);
		coInit = new Coordonne(co.x, co.y);
		taille = 30;
		couleurPrincipale = Color.RED;
		couleurSecondaire = new Color(250, 224, 173);
	}
	
	
	/*-------------------------------GETTERS------------------------*/
	
	/**
	 * Renvoie la direction de l'oiseau
	 * @return
	 */
	public double getDirectionY() {
		return directionY;
	}
	
	/**
	 * Renvoie le jeu
	 * @return
	 */
	public Jeu getJeu(){
		return j;
	}
	
	/**
	 * Renvoie l'etat de l'oiseau
	 * @return
	 */
	public boolean estLance() {
		return lance;
	}

	/**
	 * Renvoie l'angle de vol
	 * @return
	 */
	public int angle() {
		return angle;
	}
	/**
	 * Renvoie l'angle de départ
	 * @return
	 */
	public double getAngleDep() {
		return angleDep;
	}

	

	public double getVitesse() {
		return vitesse;
	}

	

	/*-------------------------------SETTERS------------------------*/
	
	/**
	 * Modifie la direction
	 * @param directionY
	 */
	public void setDirectionY(double directionY) {
		this.directionY = directionY;
	}

	/**
	 * Modifie l'etat lance
	 * @param lance
	 */
	public void setLance(boolean lance) {
		this.lance = lance;
	}
	
	/**
	 * Modifie l'angle de vol
	 * @param angle
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void setAngleDep(double d) {
		this.angleDep = d;
	}



	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}



	

}