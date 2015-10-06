import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dessin extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private RectangleImage bird = null;
	private JFrame f = new JFrame("Test");

	enum GameState {
		Running, Dead
	}

	GameState state = GameState.Running;

	public Dessin() {
		f.setLocation(800, 100);
		f.setSize(1000, 500);
		f.add(this);
		f.addKeyListener(this);
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		repaint();
	}

	public void init() throws Exception {
		bird = new RectangleImage(ImageIO.read(new File(getClass().getResource(
				"data/Bird.png").toURI())), 0, 200);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Chronometer c = new Chronometer();
		c.start();
		if (state == GameState.Running) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				bird.move(bird.getRect().x + 100, bird.getRect().y);
				System.out.println("X = " + bird.getRect().x + " " + "Y = "
						+ bird.getRect().y);
				repaint();
				break;

			case KeyEvent.VK_UP:
				bird.move(bird.getRect().x, bird.getRect().y - 100);
				repaint();
				break;

			default:
				break;
			}
		}

		else {
			c.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		if (state == GameState.Running) {
			g.drawOval(50, 100, 50, 50);
			g.setColor(Color.ORANGE);
			g.fillOval(50, 100, 50, 50);
			g.setColor(Color.YELLOW);
			g.fillArc(100, 105, 50, 50, 160, 30);
			g.setColor(Color.BLACK);
			g.drawOval(70, 110, 20, 10);
			g.setColor(Color.WHITE);
			g.fillOval(70, 110, 20, 10);
			g.setColor(Color.BLACK);
			g.fillOval(75, 110, 10, 10);
			if (bird == null) {
				try {
					g.drawImage(bird.getImg(), 0, 200, null);
					bird = new RectangleImage(ImageIO.read(new File(getClass()
							.getResource("data/Bird.png").toURI())),
							bird.getRect().x, bird.getRect().y);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (bird.getRect().intersectsLine(getWidth(), 200, 0, 0)) {
				System.out.println("Dead");
				state = GameState.Dead;
			}
			bird.draw(g, this);
			f.repaint();
			repaint();
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.ORANGE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			g.drawString("Game Over", 400, 350);
			f.removeKeyListener(this);
		}
	}
}
