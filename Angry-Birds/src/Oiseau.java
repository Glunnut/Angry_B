import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Oiseau extends JPanel{
	private static final long serialVersionUID = 1L;
	private JFrame f = new JFrame("Oiseau");
	private Rectangle r;
	private int x = 50, y = 100, height = 50, width = 50;
	
	public Oiseau(){
		f.setLocation(800, 100);
		f.setSize(1000, 500);
		f.add(this);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r = new Rectangle(x, y);
	}
	
	public void paint(Graphics g){
		g.drawOval(x, y, height, 50);
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, 50, 50);
		g.setColor(Color.YELLOW);
		g.fillArc(100, 105, 50, 50, 160, 30);
		g.setColor(Color.BLACK);
		g.drawOval(70, 110, 20, 10);
		g.setColor(Color.WHITE);
		g.fillOval(70, 110, 20, 10);
		g.setColor(Color.BLACK);
		g.fillOval(75, 110, 10, 10);
	}
}
