package Livrable2.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Livrable2.ab.Coordonne;
import Livrable2.ab.Jeu;
import Livrable2.controller.ControllerOiseau;
import Livrable2.model.ModelOiseau;

public class VueOiseau extends Vue {

	/*-------------------------------ATTRIBUTS------------------------*/
	
	//Coordonnee x et y
	private int x, y = 0;
	
	//Coordonnee initial de l'oiseau
	private Coordonne coInit = null;
	
	//Coordonnee courante de l'oiseau
	private Coordonne co ;
	
	//Taille de l'oiseau
	private int taille = 30;
	
	//Couleur de l'oiseau
	private Color couleurPrincipale, couleurSecondaire;
	
	//Creation d'un rectangle
	private Rectangle rect;
	
	//Etat de l'oiseau avant de toucher un obstacle ou le sol
	public static boolean touche = false;
	
	//Attribut en lien avec la classe rotate
	BufferedImage img = null;
	ImageTool option;
	private int angle=0;
	
	/*-------------------------------CONSTRUCTEURS------------------------*/
	
	/**
	 * Constructeur de la vue de l'oiseau
	 * @param m
	 * @param c
	 */
	public VueOiseau(ModelOiseau m, ControllerOiseau c) {
		super.model = m;
		this.controller = c;
		rect = new Rectangle(x, y, taille, taille);

		coInit = model.getCoInit();
		taille = model.getTaille();
		co = model.getCo();
		couleurPrincipale = model.getCouleurPrincipale();
		couleurSecondaire = model.getCouleurSecondaire();
		angle = m.angle();
	}
	
	/*-------------------------------GETTERS------------------------*/
	
	/**
	 * Renvoie la coordonnee x de l'oiseau
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Renvoie la coordonnee y de l'oiseau
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Renvoie l'angle de l'oiseau
	 */
	public int getAngle(){
		return angle;
	}
	
	/**
	 * Renvoie le rectangle
	 */
	public Rectangle getRect() {
		return this.rect;
	}
	
	/*-------------------------------SETTERS------------------------*/
	
	/**
	 * Modification de l'angle de l'oiseau
	 * @param angle
	 */
	public void setAngle(int angle){
		 this.angle=angle;
	}
	
	/**
	 * Modifie l'etat de l'oiseau
	 * @param touche
	 */
	public void setTouche(boolean touche){
		this.touche = touche;
	}

	/*-------------------------------METHODES------------------------*/

	/**
	 * Fonction de deplacement de l'oiseau
	 */
	public void move(int x, int y) {
		if (!touche){
			getModel().setCo(new Coordonne(x, y));
		this.x = x;
		this.y = y;
		this.rect.setBounds(x, y, rect.width, rect.height);
		}
			
	}
	
	/**
	 * Affichage de l'oiseau sur la frame	
	 */
	public void paintComponent(Graphics g) {
		try {
		    img = ImageIO.read(new File("res/Pingouin1.png"));
		} catch (IOException e) {
		}
		
		g.drawImage(option.rotate(img, 40-(angle)),x-20,y-45,null);
		
	}
	
	/**
	 * Update de la vue
	 */
	@Override
	public void update(Observable o, Object arg) {
		
	}


}