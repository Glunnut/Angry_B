package Livrable2.ab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Livrable2.controller.ControllerObstacle;
import Livrable2.controller.ControllerOiseau;
import Livrable2.model.ModelObstacle;
import Livrable2.model.ModelOiseau;
import Livrable2.view.Vue;
import Livrable2.view.VueObstacle;
import Livrable2.view.VueOiseau;

public class Jeu extends JPanel implements MouseMotionListener {

	/*-------------------------------ATTRIBUTS------------------------*/
	private Point p1 = new Point(20, 350), p2, p3, oiseau;
	protected JFrame f;
	Random r = new Random();
	double t = 0.0;

	private ArrayList<Vue> objetsVue = new ArrayList<>();
	ArrayList<Point> trace = new ArrayList<>();
	Courbe courbe;
	Courbe courbe1;
	private List<VueObstacle> obstacles = new ArrayList<VueObstacle>();
	int width = 900, height = 500, nbrebond = 0;
	private boolean sorti = false;
	private boolean touche = false;
	private int affichage = 0;
	private Rectangle sol = new Rectangle(0, 445, 900, 80);
	boolean solTouch = false;
	ModelOiseau modelOiseau = new ModelOiseau();
	ControllerOiseau controllerOiseau = new ControllerOiseau(modelOiseau);
	VueOiseau o = new VueOiseau(modelOiseau, controllerOiseau);

	/*-------------------------------CONSTRUCTEURS------------------------*/
	public Jeu(int nb) {

		creationOsbtacles(nb);
		configFrame();
		o.move(100, 370);
		addMouseMotionListener(this);
		//go();
	}

	/*-------------------------------METHODES------------------------*/
	public void go() {
		t = 0;
		Double t2 = 0.0;
		System.out.println(o.getModel().getCo());
		do {
			p2 = new Point(140, r.nextInt(this.getHeight()));
			p3 = new Point(this.getWidth(), r.nextInt(this.getHeight()));
		} while (p2.y > 250);

		/* Decommentez les 2 points pour tester les rebonds */
		// p2 = new Point(200, 150);
		// p3 = new Point(300, 400);

		while (!touche && !sorti) {
			System.out.println(sorti);
			affichage++;
			if (solTouch) {
				solTouch = false;
				while (nbrebond < 10 || !sorti) {
					t2 += 0.01;
					Point p2bis = new Point(
							(int) (p2.getX() + ((p2.getX() - p1.getX()) * 2)),
							(int) (p2.getY()) + 40);
					Point p3bis = new Point(
							(int) (p3.getX() + ((p3.getX() - p2.getX()) * 2)),
							(int) (p3.getY()) + 30);
					courbe = new Courbe(oiseau, p2bis, p3bis, t2);
					Point reb = courbe.getPt();

					o.move((int) reb.getX(), (int) reb.getY());
					variationObstacle();
					repaint();
					if (solTouch) {
						t2 = 0.0;
						solTouch = false;
						p1 = oiseau;
						p2 = p2bis;
						p3 = p3bis;
						oiseau = new Point(o.getX(), 379);
					}
					attente(40);
				}

			} else {
				t = t + 0.01;
				courbe = new Courbe(p1, p2, p3, t);
				Point act = courbe.getPt();
				courbe1 = new Courbe(p1, p2, p3, t + 0.1);
				Point reb1 = courbe1.getPt();
				o.setAngle(act.y - reb1.y);
				trace.add(act);
				o.move((int) act.getX(), (int) act.getY());
			}
			variationObstacle();
			repaint();
			attente(40);

		}

	}

	// }
	/*
	 * Fonction qui permet d'attendre un certain temps
	 */
	private void attente(int delay) {
		int attente = delay;
		// Temps d'attente en millisecondes
		Date date = new Date();
		long debut = date.getTime();
		// R�cup�re la date courante en millisecondes
		long somme = debut + attente;
		// Date � laquelle on sortira de la fonction
		while (debut < somme) {
			date = new Date();
			debut = date.getTime();
		}
	}

	public void variationObstacle() {
		for (VueObstacle o : obstacles)
			if (affichage < 40 || (affichage > 80 && affichage < 120)
					|| affichage > 160)
				if (o.getForme().equals("rond"))
					o.setX(o.getX() - 1);
				else {
					o.setX(o.getX() - 1);
					o.setY(o.getY() - 1);
				}
			else if (o.getForme().equals("rond"))
				o.setX(o.getX() + 1);
			else {
				o.setX(o.getX() + 1);
				o.setY(o.getY() + 1);
			}
	}

	private boolean isTouche() {
		return touche;
	}

	public void setTouche(boolean touche) {
		this.touche = touche;
	}

	public void configFrame() {
		f = new JFrame("AngryBirds");
		f.add(this);
		f.setResizable(false);
		f.setSize(width, height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void creationOsbtacles(int nb) {
		for (int i = 0; i < nb; i++) {
			ModelObstacle modelObs = new ModelObstacle(
					r.nextInt(840 - 740 + 1) + 740,
					r.nextInt(400 - 60 + 1) + 60);
			ControllerObstacle controllObs = new ControllerObstacle();
			VueObstacle vueObs = new VueObstacle(modelObs, controllObs);
			modelObs.addObserver(vueObs);
			obstacles.add(vueObs);
		}
	}

	public void affichagePointilles(Graphics g) {
		for (int i = 0; i < trace.size(); i += 2) {
			if (!touche)
				g.fillOval(trace.get(i).x + 5, trace.get(i).y + 8, 5, 5);
		}
	}

	public boolean verifColisionOuSorti() {
		sorti = false;
		touche = false;
		for (final VueObstacle obs : obstacles) {
			if (o.getRect().intersects(obs.getRec())) {
				obs.setTouche(true);
				o.setTouche(true);
				touche = true;
				obs.repaint();
				Timer timer = new Timer();

				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						obs.setTouche(false);
					}
				}, 0, 500);
				return true;
			}
		}

		if (o.getRect().getX() > width + 5 || o.getRect().getY() < 0
				|| o.getRect().getX() < 0 || o.getRect().getY() > height) {
			System.out.println("sorti");
			sorti = true;
		}
		if (o.getRect().intersects(sol)) {
			oiseau = new Point(o.getX(), o.getY());
			nbrebond++;
			solTouch = true;
		}
		return false;
	}

	public void paintComponent(Graphics g) {
		// System.out.println("repaint");
		super.paintComponent(g);

		g.drawImage(new ImageIcon("res/bg_menu.png").getImage(), 0, 0, null);
		g.setColor(Color.orange);
		affichagePointilles(g);
		verifColisionOuSorti();
		g.drawImage(new ImageIcon("res/angryb.png").getImage(), 0, 445, null);
		g.setColor(Color.blue);
		// ((Graphics2D) g).fill(sol);
		for (VueObstacle obs : obstacles) {
			obs.paintComponent(g);
		}
		o.paintComponent(g);

		// affichageVitesse();
	}

	public void addEnnemi(Vue vue) {
		objetsVue.add(vue);
	}

	public ArrayList<Vue> getObjetsScene() {
		return objetsVue;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		o.move(e.getX(), e.getY());
		System.out.println(o.getModel().getCo());
		if(e.getX() < 20)
			o.move(20, o.getY());
		if(e.getX() > 180)
			o.move(180, o.getY());
		if(e.getY() > 425)
			o.move(o.getX(), 425);
		if(e.getY() < 315)
			o.move(o.getX(), 315);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}