package Livrable2.model;

import java.awt.Color;
import java.util.Observable;

import Livrable2.ab.Coordonne;

public abstract class Model extends Observable {

	/*-------------------------------ATTRIBUTS------------------------*/

	// Creation coordonnees
	Coordonne co;
	Coordonne coInit;

	// Creation des couleurs
	Color couleurPrincipale = Color.RED;
	Color couleurSecondaire;

	// Definition de la taille
	int taille = 10;

	/*-------------------------------GETTERS------------------------*/

	/**
	 * Renvoi de la coordonne co
	 * @return
	 */
	public Coordonne getCo() {
		return co;
	}
	
	/**
	 * Renvoi de la coordonne coInit
	 * @return
	 */
	public Coordonne getCoInit() {
		return coInit;
	}

	/**
	 * Renvoi de la couleur principale
	 * @return
	 */
	public Color getCouleurPrincipale() {
		return couleurPrincipale;
	}

	/**
	 * Renvoi de la couleur secondaire
	 * @return
	 */
	public Color getCouleurSecondaire() {
		return couleurSecondaire;
	}

	/**
	 * Renvoi de la taille
	 * @return
	 */
	public int getTaille() {
		return taille;
	}

	

	/*-------------------------------SETTERS------------------------*/
	
	/**
	 * Changement de la coordonne co
	 * @param co
	 */
	public void setCo(Coordonne co) {
		this.co = co;
		notifyObservers();
	}

}