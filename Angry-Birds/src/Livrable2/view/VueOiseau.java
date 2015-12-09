package Livrable2.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Observable;

import javax.swing.ImageIcon;

import Livrable2.ab.Coordonne;
import Livrable2.ab.Jeu;
import Livrable2.controller.ControllerOiseau;
import Livrable2.model.ModelOiseau;

public class VueOiseau extends Vue {

	
	private int x, y = 0;
	private Coordonne coInit = null;
	private int taille = 30;
	private Coordonne co ;
	private Color couleurPrincipale, couleurSecondaire;
	private Rectangle rect;
	public static boolean touche = false;
	ImageIcon ping = new ImageIcon("res/Pingouin1.png");
	
	public VueOiseau(ModelOiseau m, ControllerOiseau c) {
		super.model = m;
		this.controller = c;
		rect = new Rectangle(x, y, taille, taille);

		coInit = model.getCoInit();
		taille = model.getTaille();
		co = model.getCo();
		couleurPrincipale = model.getCouleurPrincipale();
		couleurSecondaire = model.getCouleurSecondaire();

	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Rectangle getRect() {
		return this.rect;
	}

	public void move(int x, int y) {
		if (!touche){
			getModel().setCo(new Coordonne(x, y));
		this.x = x;
		this.y = y;
		this.rect.setBounds(x, y, rect.width, rect.height);
		}
		
			
		
	}
	
	public void setTouche(boolean touche){
		this.touche = touche;
	}

	public void paintComponent(Graphics g) {
		//System.out.println("repaint oiseau");
		System.out.println(this.x + "   " +this.y);
		AffineTransform rotation = new AffineTransform();
		rotation = AffineTransform.getRotateInstance(45,(int)(ping.getImage().getWidth(null)/2),(int)(ping.getImage().getHeight(null)/2));
		g.drawImage(new ImageIcon("res/Pingouin1.png").getImage(),x-20,y-5,null);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}