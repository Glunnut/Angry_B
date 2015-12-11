package Livrable2.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
	private int x, y = 0;
	private Coordonne coInit = null;
	private int taille = 30;
	private Coordonne co ;
	private Color couleurPrincipale, couleurSecondaire;
	private Rectangle rect;
	public static boolean touche = false;
	BufferedImage img = null;
	ImageTool option;
	private int angle=0;
	
	/*-------------------------------CONSTRUCTEURS------------------------*/
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
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getAngle(){
		return angle;
	}
	public Rectangle getRect() {
		return this.rect;
	}
	
	/*-------------------------------SETTERS------------------------*/
	
	public void setAngle(int angle){
		 this.angle=angle;
	}
	
	public void setTouche(boolean touche){
		this.touche = touche;
	}

	/*-------------------------------METHODES------------------------*/

	public void move(int x, int y) {
		if (!touche){
			getModel().setCo(new Coordonne(x, y));
		this.x = x;
		this.y = y;
		this.rect.setBounds(x, y, rect.width, rect.height);
		}
			
	}
		
	public void paintComponent(Graphics g) {
		//System.out.println("repaint oiseau");
		System.out.println(this.x + "   " +this.y);
		
		try {
		    img = ImageIO.read(new File("res/Pingouin1.png"));
		} catch (IOException e) {
		}
		if(angle<0)
		g.drawImage(option.rotate(img, 40-(angle)),x-20,y-45,null);
		else
			g.drawImage(option.rotate(img, 40-(angle)),x-20,y-45,null);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
}