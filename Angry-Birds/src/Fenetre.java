import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	Menu menu;
	

	public Fenetre(){
		
		configFrame();
		setVisible(true);
		//pack();
	}
	
	public void configFrame(){
		this.setTitle("Menu Angry Birds");
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		this.setSize(800, 600);
		this.setResizable(false);
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
		this.setVisible(true);
		this.pack();
		
		
		
		
		
		
		
	}
	
	
}