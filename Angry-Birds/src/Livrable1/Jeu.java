package Livrable1;

import java.awt.Point;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;

public class Jeu extends JFrame {

	// Serial
	private final long serialVersionUID = 1L;

	// Instanciation d'un panneau
	private Panneau pan = new Panneau();

	// Instanciation d'un point
	private Point p1 = new Point(20, 350), p2, p3;

	// Instanciation d'un random
	private Random r = new Random();

	// taille de l'écran
	static int x = 900, y = 500;

	// initialisation du nombre de lancer et du nombre de lancer effectue
	private int nb = 0, i = 0;

	// initialisation d'un booleen
	private static boolean touche = false;

	static boolean sorti = false;

	// Creation d'un objet trajectoire
	private Trajectoire traj;

	private int n = 0, affichage = 0;

	/**
	 * Constructeur
	 * 
	 * @param title
	 */
	public Jeu(String title) {
		super(title);
		EventListener evt = new EventListener(this);
		getPan().addMouseListener(evt);
	}

	/**
	 * Configuration de la Frame
	 */
	public void configFrame() {
		this.setSize(x, y);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getPan().creationOsbtacles();
		this.setContentPane(getPan());
		this.setVisible(true);
	}

	/**
	 * Lancement du jeu
	 * 
	 * @param nbLancer
	 */
	public void lancerJeu(int nbLancer) {
		this.nb = nbLancer;
		System.out.println("jeu lancé");
		go();
	}

	/**
	 * reinitialise la vue
	 */
	public void reinit() {
		setTouche(false);
		sorti = false;
		getPan().setPosX(p1.x);
		getPan().setPosY(p1.y);
		getPan().repaint();
	}

	public void variationObstacle() {
		for (Obstacle o : Obstacle.obstacles)
			if (affichage < 40 || (affichage > 80 && affichage < 120)
					|| affichage > 160)
				if (o.getForme().equals("rond"))
					o.setPosX(o.getPosX() - 1);
				else {
					o.setPosX(o.getPosX() - 1);
					o.setPosY(o.getPosY() - 1);
				}
			else if (o.getForme().equals("rond"))
				o.setPosX(o.getPosX() + 1);
			else {
				o.setPosX(o.getPosX() + 1);
				o.setPosY(o.getPosY() + 1);
			}
	}

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

	/**
	 * Demarre le mouvement de l'oiseau
	 */
	void go() {
		System.out.println("go()");
		affichage = 0;
		reinit();
		getPan().setLabel(
				"Lancer n�" + (i + 1) + ". Il en reste " + ((nb - i) - 1));
		getPan().repaint();
		double t = 0;
		do {
			System.out.println("do");
			p2 = new Point(140, r.nextInt(this.getHeight()));
			p3 = new Point(this.getWidth(), r.nextInt(this.getHeight()));
		} while (p2.y > 250);

		while ((!isTouche() || !sorti) && i < nb) {
			System.out.println("while " + getPan().getPosY());
			affichage++;
			t = t + 0.01;
			if ((i + 1) % 2 == 0)
				traj = new Trajectoire(p1, p2, p3, t);
			else {
				if (n % 2 == 0) {
					traj = new Trajectoire(p1, t, this);
				} else {
					traj = new Trajectoire(p1, t);
				}
			}
			Point rep = new Point(traj.getPt());
			getPan().setPosX(rep.x);
			getPan().setPosY(rep.y);
			
			variationObstacle();

			getPan().repaint();
			attente(40);

			if (isTouche() || sorti) {
				if ((i + 1) % 2 == 0)
					n++;
				reinit();
				restart();
			}
		}
		reinit();
	}

	/**
	 * Redemarre le mouvement de l'oiseau
	 */
	public void restart() {
		if (isTouche()) {
			attente(2000);
		} else {
			attente(1000);
		}
		i++;
		if (i < nb) {
			go();
		} else {
			getPan().setLabel("Lancers termin�s");
		}
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public static boolean isTouche() {
		return touche;
	}

	public static void setTouche(boolean touche) {
		Jeu.touche = touche;
	}

	public Panneau getPan() {
		return pan;
	}

}
