
package Livrable1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	private static final long serialVersionUID = 1L;

	// Creation d'objets Obstacle
	private Obstacle o1, o2, o3, o4, o5, o6, o7, o8;

	private JLabel label;

	// Coordonnee X et Y d'un oiseau
	private int posX = 30;
	private int posY = 360;

	// Instanciation d'un oiseau
	private Oiseau oiseau = new Oiseau(posX, posY);

	// Instanciation d'une liste de points
	private ArrayList<Point> pts = new ArrayList<Point>();

	/**
	 * Constructeurs
	 * 
	 */
	public Panneau() {
		addLabel();
	}

	public void addLabel() {
		label = new JLabel("");
		this.add(label);
	}

	/**
	 * Creation D'obstacles
	 */
	public void creationOsbtacles() {
		o1 = new Obstacle(750, 200);
		o2 = new Obstacle(770, 60);
		o3 = new Obstacle(800, 450);
		o4 = new Obstacle(770, 300);
		o5 = new Obstacle(790, 154);
		o6 = new Obstacle(830, 250);
		o7 = new Obstacle(820, 385);
		o8 = new Obstacle(825, 100);

	}

	/**
	 * Verification de collision
	 */
	public void verifColisionOuSorti() {
		for (final Obstacle o : Obstacle.obstacles) {
			if (oiseau.getRect().intersects(o.getRec())) {
				o.setColObs(Color.RED);
				Jeu.setTouche(true);
				pts.removeAll(pts);

				Timer timer = new Timer();

				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						o.setColObs(Color.BLUE);

					}
				}, 0, 500);
			}
		}

		if (oiseau.getRect().getX() > Jeu.x + 5 || oiseau.getRect().getY() > Jeu.y + 5 || oiseau.getRect().getY() < 0
				|| oiseau.getRect().getX() < 0) {
			Jeu.sorti = true;
			pts.removeAll(pts);
		}
	}

	/**
	 * affichage pointilles
	 * 
	 * @param g
	 */
	public void affichagePointilles(Graphics g) {
		for (int i = 0; i < pts.size(); i += 2) {
			g.fillOval(pts.get(i).x + 5, pts.get(i).y + 8, 5, 5);
		}
		pts.add(new Point(posX, posY));
	}
	public void affichageVitesse(){
		
		if(pts.size()>1){
			Point prec = pts.get(0);
		for(int i = 0; i<pts.size();i++){
			Point act = pts.get(i);
			System.out.println(Outils.distance(prec, act)+"cm/s");
			prec = act;
		}
		}
	}
	/**
	 * affichage des éléments de la fenêtre
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("res/bg_menu.png").getImage(), 0, 0, null);
		g.setColor(Color.orange);
		affichagePointilles(g);
		verifColisionOuSorti();
		g.setColor(Color.blue);
		Obstacle.afficher(g);
		oiseau.afficher(g);
		affichageVitesse();
		if (isIncreasing(posY))
			oiseau.move(posX, posY + 20);
		else 
			oiseau.move(posX, posY - 20);
	}

	// ---------------------GETTERS--------------------------//

	public ArrayList<Point> getPts() {
		return pts;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public JLabel getLabel() {
		return label;
	}

	// ---------------------SETTERS--------------------------//

	public void setPts(ArrayList<Point> pts) {
		this.pts = pts;
	}

	public void setPosX(int posX) {
		this.posX = (int) posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setLabel(String txt) {
		this.label.setText(txt);
	}

	// ---------------------Verification x et y--------------------------//

	public boolean isIncreasing(int something) {
		if (something < 0)
			something = -something;

		int last = 200;
		int x;
		while (something > 0) {
			x = something % 10;
			if (last < x)
				return false;
			last = x;
			something /= 10;
		}
		return true;
	}
}
