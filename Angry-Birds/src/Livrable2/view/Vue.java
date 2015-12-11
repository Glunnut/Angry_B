package Livrable2.view;


import java.util.Observer;

import javax.swing.JPanel;

import Livrable2.controller.Controller;
import Livrable2.model.Model;


public abstract class Vue extends JPanel implements Observer{
	
	/*-------------------------------ATTRIBUTS------------------------*/
	
	//Creation d'un modele
	public Model model;
	
	//Creation d'un controlleur
    public Controller controller;
   
    /*-------------------------------GETTERS------------------------*/
    
    //Renvoie le controlleur
    public Controller getController() {
        return controller;
    }

    //Renvoie le modele
    public Model getModel() {
        return model;
    }
    
   
}
