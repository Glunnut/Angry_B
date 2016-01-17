package Livrable2.ab;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	static List<VueObstacle> obstacles = new ArrayList<VueObstacle>();

	// Taille de l'ecran et nombre de rebond
	int width = 900, height = 520, nbrebond = 0;

	// Creation de booleen pour sortie, touche et lancement du jeu
	private boolean sorti = false;
	private boolean touche = false;
	private boolean go = false;
	private boolean solTouch = false;
	private boolean end = false;
	public static boolean mute = false;
	// Creation d'une variable affichage
	private int affichage = 0;

	// Instanciation du sol
	private Rectangle sol = new Rectangle(0, 445, 900, 80);

	// MVC
	ModelOiseau modelOiseau = new ModelOiseau(this);
	ControllerOiseau controllerOiseau = new ControllerOiseau(modelOiseau);
	VueOiseau o = new VueOiseau(modelOiseau, controllerOiseau);

	ModelObstacle modelOb = new ModelObstacle(r.nextInt(840 - 740 + 1) + 740, r.nextInt(400 - 60 + 1) + 60);
	ControllerObstacle controllOb = new ControllerObstacle();
	VueObstacle vueOb = new VueObstacle(modelOb, controllOb);

	// Variable de gestion du lancer
	int y1 = 0, i = 0;

	// Creation du systeme de point de vie
	private int vie = 0;

	// Ajout du son
	private AudioInputStream input;
	private Clip clip;

	// Ajout de la partie option
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Menu");
	private JMenuItem menuItem;
	private JMenuItem sons;

	private boolean cimage = true;
	private int open = 0;
	JFrame opt;
	JPanel jpan;
	JComboBox<Integer> rond;
	JComboBox<Integer> carre;
	static final int MIN = 0;
	static final int MAX = 30;
	static final int INIT = 0;

	private int resAir = 0;
	JSlider resistAir;

	ObstacleFactory obsf;

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
		/*while (collide(obstacles)) {
			for (int i = 0; i < obstacles.size(); i++) {
				obstacles.get(i).setX(r.nextInt(840 - 740 + 1) + 740);
				obstacles.get(i).setY(r.nextInt(400 - 60 + 1) + 60);
				System.out.println("X(i) = " + obstacles.get(i).getX() + ", Y(i) = " + obstacles.get(i).getY());
			}
		}*/

	}

	public boolean collide(List<VueObstacle> v) {
		for (int i = 1; i < v.size() - 1; i++) {
			if (v.get(i).getRec().intersects(v.get(i + 1).getRec()))
				return true;
			if (v.get(i).getRec().intersects(v.get(i - 1).getRec()))
				return true;
		}
		return false;
	}

	/**
	 * Configuration de la Frame
	 */
	public void configFrame() {
		menuBar.add(menu);
		sons = new JMenuItem(new ImageIcon("res/son.png"));
		sons.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (cimage) {
					sons.setIcon(new ImageIcon("res/soncoupe.png"));
					cimage = false;
					clip.stop();
					mute = true;
				} else {
					mute = false;
					sons.setIcon(new ImageIcon("res/son.png"));
					cimage = true;
					clip.start();
				}
			}
		});

		menuBar.add(sons);
		menuItem = new JMenuItem("Option", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (getOpen() == 0) {
					setOpen(1);
					opt = new JFrame("Option");
					opt.setSize(300, 300);
					opt.setLocationRelativeTo(null);
					opt.setResizable(false);
					opt.setAlwaysOnTop(true);
					opt.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							setOpen(0);
						}

						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});

					JPanel ob = new JPanel();
					ob.setPreferredSize(new Dimension(250, 100));
					ob.setBorder(BorderFactory.createTitledBorder("Nombre d\' obstacle"));
					JLabel obsrond = new JLabel("Obstacle rond : ");

					rond = new JComboBox<Integer>();
					rond.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (rond.getSelectedIndex() == 6) {
								carre.setSelectedIndex(0);
							} else if (rond.getSelectedIndex() == 5) {
								carre.setSelectedIndex(1);
							} else if (rond.getSelectedIndex() == 4) {
								carre.setSelectedIndex(2);
							}

							if (rond.getSelectedIndex() + carre.getSelectedIndex() > 6) {
								if (rond.getSelectedIndex() > carre.getSelectedIndex()) {
									if (rond.getSelectedIndex() == 6) {
										carre.setSelectedIndex(0);
									} else if (rond.getSelectedIndex() == 5) {
										carre.setSelectedIndex(1);
									} else if (rond.getSelectedIndex() == 4) {
										carre.setSelectedIndex(2);
									}
								}

							} else if (rond.getSelectedIndex() + carre.getSelectedIndex() > 6) {
								if (rond.getSelectedIndex() < carre.getSelectedIndex()) {
									if (carre.getSelectedIndex() == 6) {
										rond.setSelectedIndex(0);
									} else if (rond.getSelectedIndex() == 5) {
										rond.setSelectedIndex(1);
									} else if (rond.getSelectedIndex() == 4) {
										rond.setSelectedIndex(2);
									}
								}
							}
						}
					});

					JLabel obscarre = new JLabel("Obstacle carre : ");

					carre = new JComboBox<Integer>();
					carre.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if (carre.getSelectedIndex() == 6) {
								rond.setSelectedIndex(0);
							} else if (carre.getSelectedIndex() == 5) {
								rond.setSelectedIndex(1);
							} else if (carre.getSelectedIndex() == 4) {
								rond.setSelectedIndex(2);
							}

							if (rond.getSelectedIndex() + carre.getSelectedIndex() > 6) {
								if (rond.getSelectedIndex() > carre.getSelectedIndex()) {
									if (rond.getSelectedIndex() == 6) {
										carre.setSelectedIndex(0);
									} else if (rond.getSelectedIndex() == 5) {
										carre.setSelectedIndex(1);
									} else if (rond.getSelectedIndex() == 4) {
										carre.setSelectedIndex(2);
									}
								}

							} else if (rond.getSelectedIndex() + carre.getSelectedIndex() > 6) {
								if (rond.getSelectedIndex() < carre.getSelectedIndex()) {
									if (carre.getSelectedIndex() == 6) {
										rond.setSelectedIndex(0);
									} else if (rond.getSelectedIndex() == 5) {
										rond.setSelectedIndex(1);
									} else if (rond.getSelectedIndex() == 4) {
										rond.setSelectedIndex(2);
									}
								}
							}
						}
					});

					for (int i = 0; i <= 6; i++) {
						rond.addItem(i);
						carre.addItem(i);

					}

					jpan = new JPanel();
					ob.setLayout(new GridLayout(2, 2));
					ob.add(obsrond);
					ob.add(rond);
					ob.add(obscarre);
					ob.add(carre);
					JButton okBouton = new JButton("OK");
					Dimension boutonTaille = new Dimension(100, 30);
					okBouton.setMaximumSize(boutonTaille);
					okBouton.setMinimumSize(boutonTaille);
					okBouton.setPreferredSize(boutonTaille);
					okBouton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							for (int i = obstacles.size() - 1; i >= 0; i--) {
								obstacles.remove(obstacles.get(i));
							}
							obsf = new ObstacleFactory();

							for (int i = 0; i < rond.getSelectedIndex(); i++) {
								Obstacle ob = obsf.getObstacleType("ROND");
								ob.creation();

							}

							for (int j = 0; j < carre.getSelectedIndex(); j++) {
								Obstacle ob = obsf.getObstacleType("CARRE");
								ob.creation();
							}
							while (collide(obstacles)) {
								for (int i = 0; i < obstacles.size(); i++) {
									obstacles.get(i).setX(r.nextInt(840 - 740 + 1) + 740);
									obstacles.get(i).setY(r.nextInt(400 - 60 + 1) + 60);
								}
							}
							setVie(12);
							f.repaint();
						}
					});

					resistAir = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
					resistAir.addChangeListener(new ChangeListener() {

						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							setResAir(resistAir.getValue());

						}
					});
					Font font = new Font("Serif", Font.ITALIC, 15);
					resistAir.setFont(font);
					resistAir.setMajorTickSpacing(10);
					resistAir.setMinorTickSpacing(1);
					resistAir.setPaintTicks(true);
					resistAir.setPaintLabels(true);
					jpan.add(ob);
					opt.setLayout(new BorderLayout());
					opt.add(jpan, BorderLayout.NORTH);
					opt.add(resistAir, BorderLayout.CENTER);
					opt.add(okBouton, BorderLayout.SOUTH);
					opt.setVisible(true);

				}
			}
		});

		menu.add(menuItem);
		f = new JFrame("AngryBirds");
		f.setJMenuBar(menuBar);
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
			if (!mute) {
				clip.open(input);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
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
			// variationObstacle();
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
		if (clip.isActive())
			clip.close();
		try {
			input = AudioSystem.getAudioInputStream(new File("res/Bump.wav"));
			clip = AudioSystem.getClip();
			if (!mute) {
				clip.open(input);
				clip.loop(0);
			}
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
			System.exit(1);
		}

		if (obstacles.isEmpty()) {
			JOptionPane.showMessageDialog(f, "Vous avez gagné", "il n\'y a plus d\'obstacle",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("res/kingorqueen.jpg"));
			System.exit(1);
		}

	}

	/**
	 * Redemarre le mouvement de l'oiseau
	 */
	public void restart() {
		for (VueObstacle i : obstacles)
			i.setTouche(false);
		if (isTouche()) {
			attente(2000);
		} else {
			attente(1000);
		}
		// i++;
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
	 * Fonction de verification collision avec obstacles ou sorti de l'ï¿½cran
	 * 
	 * @return
	 */
	public boolean verifCollisionOuSorti() {
		sorti = false;
		touche = false;
		for (int i = obstacles.size() - 1; i >= 0; i--) {
			if (o.getRect().intersects(obstacles.get(i).getRec()) && !obstacles.get(i).getTouche()) {
				// obstacles.remove(obstacles.get(i));
				System.out.println("Vitesse = " + modelOiseau.getVitesse() + ", Vie = " + obstacles.get(i).getVie());
				if (obstacles.get(i).getVie() - ((int) modelOiseau.getVitesse() - getResAir()) < 0) {
					obstacles.get(i).setVie(0);
				} else {
					obstacles.get(i)
							.setVie((obstacles.get(i).getVie() - ((int) modelOiseau.getVitesse() - getResAir())));
				}
				obstacles.get(i).setX(obstacles.get(i).getX() + (((int) modelOiseau.getVitesse() - getResAir())) - 20);

				obstacles.get(i).setTouche(true);
				if (obstacles.get(i).getX() >= 880) {
					obstacles.get(i).setVie(0);
				}
				System.out.println("Vitesse = " + modelOiseau.getVitesse() + ", Vie = " + obstacles.get(i).getVie());
				if (obstacles.get(i).getVie() <= 0)
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
		g.drawString("Vitesse", 580, 30);
		g.drawString("" + (int) modelOiseau.getVitesse(), 700, 30);
		g.drawString("RA", 770, 30);
		g.drawString("" + getResAir(), 830, 30);
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

	/**
	 * Retourne l'objet obstacle
	 * 
	 * @return
	 */
	public VueObstacle getObstacle() {
		return vueOb;
	}

	public ModelOiseau getModel() {
		return this.modelOiseau;
	}

	public int getResAir() {
		return resAir;
	}

	public int getVie() {
		return vie;
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

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public void setResAir(int resAir) {
		this.resAir = resAir;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

}