package Livrable2.ab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
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

public class Jeu extends JPanel {
	private Point p1 = new Point(20, 350), p2, p3;
	protected JFrame f;
	Random r = new Random();
	double t = 0.0;
	VueOiseau o = null;
	private ArrayList<Vue> objetsVue = new ArrayList<>();

	ArrayList<Point> trace = new ArrayList<>();
	Courbe courbe;
	private List<VueObstacle> obstacles = new ArrayList<VueObstacle>();
	int width = 900, height = 500;
	private boolean sorti = false;
	private boolean touche = false;
	private int affichage = 0;

	public Jeu(int nb) {

		creationOsbtacles(nb);
		configFrame();
		ModelOiseau modelOiseau = new ModelOiseau();
		ControllerOiseau controllerOiseau = new ControllerOiseau(modelOiseau);
		o = new VueOiseau(modelOiseau, controllerOiseau);
		go();
	}
	public void go(){
		
		System.out.println(o.getModel().getCo());
		do {
			p2 = new Point(140, r.nextInt(this.getHeight()));
			p3 = new Point(this.getWidth(), r.nextInt(this.getHeight()));
		} while (p2.y > 250);
		System.out.println(p2);
		System.out.println(p3);
		while ((!isTouche() || !sorti)) {
			affichage++;
			t = t + 0.01;
			courbe = new Courbe(p1, p2, p3, t);
			Point act = new Point(courbe.getPt());
			//System.out.println(act);
			trace.add(act);
			o.move((int) act.getX(), (int) act.getY());
			// o.getModel().setCo(new Coordonne((int)act.getX(),
			// (int)act.getY()));
			variationObstacle();
			repaint();
		}
	}
	public void variationObstacle() {
		for (VueObstacle o : obstacles)
			if (affichage < 40 || (affichage > 80 && affichage < 120)
					|| affichage > 160)
				if (o.getForme().equals("rond"))
					o.getModel().setCo(new Coordonne(o.getX() - 1, o.getY()));
				else {
					o.getModel().setCo(
							new Coordonne(o.getX() - 1, o.getY() - 1));
				}
			else if (o.getForme().equals("rond"))
				o.getModel().setCo(new Coordonne(o.getX() + 1, o.getY()));
			else {
				o.getModel().setCo(new Coordonne(o.getX() + 1, o.getY() + 1));
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
			g.fillOval(trace.get(i).x + 5, trace.get(i).y + 8, 5, 5);
		}
	}

	public void verifColisionOuSorti() {
		for (final VueObstacle obs : obstacles) {
			if (o.getRect().intersects(obs.getRec())) {
				obs.touche = true;
				setTouche(true);
				obs.repaint();
				Timer timer = new Timer();

				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						obs.touche = false;

					}
				}, 0, 500);
			}
		}

		if (o.getRect().getX() > width + 5 || o.getRect().getY() > height + 5
				|| o.getRect().getY() < 0 || o.getRect().getX() < 0) {
			sorti = true;
		}
	}

	public void paintComponent(Graphics g) {
	//	System.out.println("repaint");
		super.paintComponent(g);
		g.drawImage(new ImageIcon("res/bg_menu.png").getImage(), 0, 0, null);
		g.setColor(Color.orange);
		affichagePointilles(g);
		verifColisionOuSorti();
		g.drawImage(new ImageIcon("res/angryb.png").getImage(), 0, 445, null);
		g.setColor(Color.blue);
		
		for (VueObstacle obs : obstacles) {
			obs.repaint();
		}

		o.paintComponent(this, g);
		// affichageVitesse();
	}

	public void addEnnemi(Vue vue) {
		objetsVue.add(vue);
	}

	public ArrayList<Vue> getObjetsScene() {
		return objetsVue;
	}
}