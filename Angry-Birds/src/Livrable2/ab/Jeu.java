package Livrable2.ab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Livrable2.controller.ControllerObstacle;
import Livrable2.controller.ControllerOiseau;
import Livrable2.model.ModelObstacle;
import Livrable2.model.ModelOiseau;
import Livrable2.view.Vue;
import Livrable2.view.VueObstacle;
import Livrable2.view.VueOiseau;

public class Jeu extends JPanel {

	/*-------------------------------ATTRIBUTS------------------------*/

	// Creation de points
	private Point p1 = new Point(20, 350), p2, p3, oiseau;

	// Creation de la Jframe
	protected JFrame f;

	// Instanciation d'un Random
	Random r = new Random();

	// Instant t
	double t = 0.0;

	// Creation liste de point et d'objets
	private ArrayList<Vue> objetsVue = new ArrayList<>();
	ArrayList<Point> trace = new ArrayList<>();

	// Creation des courbes
	Courbe courbe;
	Courbe courbe1;

	// Creation de la liste des Obstacles
	private List<VueObstacle> obstacles = new ArrayList<VueObstacle>();

	// Taille de l'ecran et nombre de rebond
	int width = 900, height = 500, nbrebond = 0;

	// Creation de booleen pour sortie, touche et lancement du jeu
	private boolean sorti = false;
	private boolean touche = false;
	private boolean go = false;
	private boolean solTouch = false;
	private boolean end = false;

	// Creation d'une variable affichage
	private int affichage = 0;

	// Instanciation du sol
	private Rectangle sol = new Rectangle(0, 445, 900, 80);

	// MVC
	ModelOiseau modelOiseau = new ModelOiseau(this);
	ControllerOiseau controllerOiseau = new ControllerOiseau(modelOiseau);
	VueOiseau o = new VueOiseau(modelOiseau, controllerOiseau);

	// Variable de gestion du lancer
	int y1 = 0, i = 0;

	// Creation du systeme de point de vie
	private int vie = 0;

	// Ajout du son
	private AudioInputStream input;
	private Clip clip;

	/*-------------------------------CONSTRUCTEURS------------------------*/

	/**
	 * Constructeur du jeu
	 * 
	 * @param nb
	 * @param nbLancer
	 */
	public Jeu(int nb, int nbLancer) {
		this.vie = nbLancer;
		creationOsbtacles(nb);
		configFrame();
		o.move(110, 320);
		addMouseMotionListener(controllerOiseau);
		addMouseListener(controllerOiseau);
		go();

	}

	/*-------------------------------METHODES------------------------*/

	/**
	 * Creation des differents obstacles et notification MVC
	 * 
	 * @param nb
	 */
	public void creationOsbtacles(int nb) {
		for (int i = 0; i < nb; i++) {
			ModelObstacle modelObs = new ModelObstacle(r.nextInt(840 - 740 + 1) + 740, r.nextInt(400 - 60 + 1) + 60);
			ControllerObstacle controllObs = new ControllerObstacle();
			VueObstacle vueObs = new VueObstacle(modelObs, controllObs);
			modelObs.addObserver(vueObs);
			obstacles.add(vueObs);
		}
	}

	/**
	 * Configuration de la Frame
	 */
	public void configFrame() {
		f = new JFrame("AngryBirds");
		f.add(this);
		f.setResizable(false);
		f.setSize(width, height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	/**
	 * Fonction d'affichage du trace de l'oiseau
	 * 
	 * @param g
	 */
	public void affichagePointilles(Graphics g) {
		for (int i = 0; i < trace.size(); i += 2) {
			if (!touche)
				g.fillOval(trace.get(i).x + 5, trace.get(i).y + 8, 5, 5);
		}
	}

	/**
	 * Fonction de lancement du jeu
	 */
	public void go() {

		t = 0;
		Double t2 = 0.0;
		try {
			input = AudioSystem.getAudioInputStream(new File("res/AngryBirdsThemeSong.wav"));
			clip = AudioSystem.getClip();
			clip.open(input);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		while ((!isTouche() || !sorti) && i <= vie) {
			affichage++;
			if (affichage == 160)
				affichage = 0;
			if (solTouch) {
				vie--;
				solTouch = false;
				/*
				 * while (nbrebond < 10) { t2 += 0.01; Point p2bis = new
				 * Point((int) (p2.getX() + ((p2.getX() - p1.getX()) * 2)),
				 * (int) (p2.getY()) + 40); Point p3bis = new Point((int)
				 * (p3.getX() + ((p3.getX() - p2.getX()) * 2)), (int)
				 * (p3.getY()) + 30); courbe = new Courbe(modelOiseau, t2);
				 * Point reb = courbe.getPt();
				 * 
				 * o.move((int) reb.getX(), (int) reb.getY());
				 * variationObstacle(); repaint(); if (solTouch) { t2 = 0.0;
				 * solTouch = false; p1 = oiseau; p2 = p2bis; p3 = p3bis; oiseau
				 * = new Point(o.getX(), 379); // } // attente(40); // if(sorti)
				 * // nbrebond=10; // }
				 */
				reinit();
				restart();
			} else {
				if (go) {
					t = t + 0.01;
					courbe = new Courbe(modelOiseau, t);
					Point act = courbe.getPt();
					Point reb1 = courbe.courbePhysique(t + 0.05, modelOiseau.getAngleDep());
					o.setAngle((reb1.y - act.y));
					trace.add(act);
					o.move((int) act.getX(), (int) act.getY());
				}
				if (isTouche() || sorti) {
					vie--;
					reinit();
					restart();

				}
			}
		//	variationObstacle();
			repaint();
			attente(40);
		}
		reinit();
	}

	/**
	 * Gestion des mouvements des obstacles
	 */
	public void variationObstacle() {
		for (VueObstacle o : obstacles)
			if (affichage < 40 || (affichage > 80 && affichage < 120) || affichage > 160)
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

	/**
	 * reinitialise la vue
	 */
	public void reinit() {
		if(clip.isActive())
			clip.close();
		try {
			input = AudioSystem.getAudioInputStream(new File("res/Bump.wav"));
			clip = AudioSystem.getClip();
			clip.open(input);
			clip.loop(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		touche = false;
		sorti = false;
		setGo(false);
		controllerOiseau.setDrag(true);
		o.reinitAngle();
		trace.removeAll(trace);
		o.move(100, 320);
		System.out.println(vie);

		repaint();
		if (vie == 0 && !end) {
			try {
				input = AudioSystem.getAudioInputStream(new File("res/mariodie.wav"));
				clip = AudioSystem.getClip();
				clip.open(input);
				clip.loop(0);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			JOptionPane.showMessageDialog(f, "Vous avez perdu", "Vies insuffisantes", JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("res/pascontent.png"));
			end = true;
		}

	}

	/**
	 * Redemarre le mouvement de l'oiseau
	 */
	public void restart() {
		System.out.println("i :"+i+" vie :"+vie);
		for(VueObstacle i : obstacles)
			i.setTouche(false);
		if (isTouche()) {
			attente(2000);
		} else {
			attente(1000);
		}
		//i++;
		if (i < vie) {
			go();

		}
	}

	/**
	 * Fonction qui permet d'attendre un certain temps
	 */
	private void attente(int delay) {
		int attente = delay;
		// Temps d'attente en millisecondes
		Date date = new Date();
		long debut = date.getTime();
		// Recupere la date courante en millisecondes
		long somme = debut + attente;
		// Date a laquelle on sortira de la fonction
		while (debut < somme) {
			date = new Date();
			debut = date.getTime();
		}
	}

	/**
	 * Fonction de verification collision avec obstacles ou sorti de l'�cran
	 * 
	 * @return
	 */
	public boolean verifCollisionOuSorti() {
		sorti = false;
		touche = false;
		for (int i = obstacles.size() - 1; i >= 0; i--) {
			if (o.getRect().intersects(obstacles.get(i).getRec()) && !obstacles.get(i).getTouche()) {
				//obstacles.remove(obstacles.get(i));
				obstacles.get(i).setVie((int) modelOiseau.getVitesse());
				obstacles.get(i).setTouche(true);
				System.out.println("Vitesse = " + modelOiseau.getVitesse() + ", Vie = " + obstacles.get(i).getVie());
				if(obstacles.get(i).getVie() <= 0)
					obstacles.remove(obstacles.get(i));
			}
		}

		if (o.getRect().getX() > width + 5 || o.getRect().getY() > height) {
			sorti = true;
		}
		if (o.getRect().intersects(sol)) {
			oiseau = new Point(o.getX(), o.getY());
			nbrebond++;
			solTouch = true;
		}
		return false;
	}

	/**
	 * Fonction qui retourne un booleen en cas d'un impact avec un obstacles
	 * 
	 * @return
	 */
	private boolean isTouche() {
		return touche;
	}

	/**
	 * Fonction d'affichage graphique de la frame
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(new ImageIcon("res/bg_menu.png").getImage(), 0, 0, null);
		g.setColor(Color.orange);
		affichagePointilles(g);
		verifCollisionOuSorti();
		g.drawImage(new ImageIcon("res/angryb.png").getImage(), 0, 445, null);
		g.setColor(Color.blue);
		g.drawImage(new ImageIcon("res/lp.png").getImage(), 100, 283, null);
		g.setColor(Color.black);
		g.setFont(new Font(" TimesRoman ", Font.BOLD, 30));
		g.drawString("" + vie, 10, 30);
		g.drawImage(new ImageIcon("res/Pingouin1.png").getImage(), 0, 50, null);
		if (go == false) {
			g2.setStroke(new BasicStroke(6));
			g.drawLine(130, 320, o.getX(), o.getY());
		}
		for (VueObstacle obs : obstacles) {
			obs.paintComponent(g);
		}
		o.paintComponent(g);

	}

	/*-------------------------------GETTERS------------------------*/

	/**
	 * Retourne les objets de la vue
	 * 
	 * @return
	 */
	public ArrayList<Vue> getObjetsScene() {
		return objetsVue;
	}

	/**
	 * Retourne l'objet oiseau
	 * 
	 * @return
	 */
	public VueOiseau getOiseau() {
		return o;
	}

	public ModelOiseau getModel() {
		return this.modelOiseau;
	}
	/*-------------------------------SETTERS------------------------*/

	/**
	 * Modifie la valeur de l'attibut touche
	 * 
	 * @param touche
	 */
	public void setTouche(boolean touche) {
		this.touche = touche;
	}

	/**
	 * Modifie la valeur de l'attibut P1
	 * 
	 * @param point
	 */
	public void setP1(Point point) {
		this.p1 = point;
	}

	/**
	 * Modifie la valeur de l'attibut go
	 * 
	 * @param b
	 */
	public void setGo(boolean b) {
		this.go = b;
	}

}