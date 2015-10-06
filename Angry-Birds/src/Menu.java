import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	public Menu() {
		this.setTitle("Menu Angry Birds");
		this.setPreferredSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new Panel(new ImageIcon("res/bg_menu.png")
				.getImage()));

		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				PlaySound son;
				try {
					son = new PlaySound("AngryBirdsTheme.wav");
					son.clip.start();
				} catch (LineUnavailableException | IOException
						| UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Menu();
	}
}
