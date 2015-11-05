package Livrable1;

import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

	// taille de l'Ã©cran
	static int x = 900, y = 500;

	// initialisation du nombre de lancer et du nombre de lancer effectue
	private int nb = 0, i = 0;

	// initialisation d'un booleen
	static boolean touche = false, sorti = false;

	// Creation d'un objet trajectoire
	private Trajectoire traj;

	/**
	 * Constructeur
	 * 
	 * @param title
	 */
	public Jeu(String title) {
		super(title);
	}

	/**
	 * Configuration de la Frame
	 */
	public void configFrame() {
		this.setSize(x, y);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		pan.creationOsbtacles();
		this.setContentPane(pan);
		this.setVisible(true);
	}

	/**
	 * Lancement du jeu
	 * 
	 * @param nbLancer
	 */
	public void lancerJeu(int nbLancer) {
		this.nb = nbLancer;
		go();
	}

	/**
	 * reinitialise la vue
	 */
	public void reinit() {
		touche = false;
		sorti = false;
		pan.setPosX(p1.x);
		pan.setPosY(p1.y);
		pan.repaint();
	}

	/**
	 * Demarre le mouvement de l'oiseau
	 */
	void go() {
		reinit();
		pan.setLabel("Lancer n°" + (i + 1) + ". Il en reste " + ((nb - i) - 1));
		pan.repaint();
		double t = 0;
		do {
			p2 = new Point(140, r.nextInt(this.getHeight()));
			p3 = new Point(this.getWidth(), r.nextInt(this.getHeight()));
		} while (p2.y > 250);

		while ((!touche || !sorti) && i < nb) {

			t = t + 0.01;
			if ((i + 1) % 2 == 0)
				traj = new Trajectoire(p1, t);
			else
				traj = new Trajectoire(p1, p2, p3, t);
			Point rep = new Point(traj.getPt());

			pan.setPosX(rep.x);
			pan.setPosY(rep.y);
			pan.repaint();

			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (touche || sorti) {
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
		try {
			if (touche) {
				Thread.sleep(2000);
			} else {
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;
		if (i < nb) {
			go();
		} else {
			pan.setLabel("Lancers terminés");
		}
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
