import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private Image img;

	public Panel(Image img) {
		this.img = img;
		Color colBtn = new Color(206,251,247);
		this.setLayout(null);
		JButton jouer = new JButton("Jouer");
		jouer.setBounds(315, 180, 150, 75);
		jouer.setBackground(colBtn);
	
		JButton opt = new JButton("Options");
		opt.setBackground(colBtn);
		opt.setBounds(315, 270, 150, 50);
		add(jouer);
		add(opt);

	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
