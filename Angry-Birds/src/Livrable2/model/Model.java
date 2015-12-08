package Livrable2.model;
import java.awt.Color;
import java.util.Observable;

import Livrable2.ab.Coordonne;

public abstract class Model extends Observable {

    Coordonne co;
    Coordonne coInit;
    Color couleurPrincipale = Color.RED;
    Color couleurSecondaire;
    int taille = 10;

    public Coordonne getCo() {
        return co;
    }

    public Color getCouleurPrincipale() {
        return couleurPrincipale;
    }

    public Color getCouleurSecondaire() {
        return couleurSecondaire;
    }

    public int getTaille() {
        return taille;
    }

    public Coordonne getCoInit() {
        return coInit;
    }

    public void setCo(Coordonne co) {
        this.co = co;
        notifyObservers();
    }

	

    
    
}