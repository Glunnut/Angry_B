package Livrable2.view;


import java.util.Observer;

import javax.swing.JPanel;

import Livrable2.controller.Controller;
import Livrable2.model.Model;


public abstract class Vue extends JPanel implements Observer{
	
	/*-------------------------------ATTRIBUTS------------------------*/
	public Model model;
    public Controller controller;
   
    /*-------------------------------GETTERS------------------------*/
    public Controller getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }
    
   
}
