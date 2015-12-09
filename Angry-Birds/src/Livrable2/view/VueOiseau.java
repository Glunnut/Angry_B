package Livrable2.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;

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

	public Rectangle getRect() {
		return this.rect;
	}

	public void move(int x, int y) {
		getModel().setCo(new Coordonne(x, y));
		this.x = x;
		this.y = y;
		this.rect.setBounds(x, y, rect.width, rect.height);
	}

	public void paintComponent(Graphics g) {
		System.out.println("repaint oiseau");
		System.out.println(this.x + "   " +this.y);
		g.drawOval(x, y, taille, taille);
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, taille, taille);
		g.setColor(Color.YELLOW);

		g.fillArc(x + 30, y - 1, taille, taille, 160, 30);
		g.setColor(Color.BLACK);
		g.drawOval(x + 8, y + 10, taille - 10, taille - 20);
		g.setColor(Color.WHITE);
		g.fillOval(x + 8, y + 10, taille - 10, taille - 20);
		g.setColor(Color.BLACK);
		g.fillOval(x + 13, y + 10, taille - 20, taille - 20);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}