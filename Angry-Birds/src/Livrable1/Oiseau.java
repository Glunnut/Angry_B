package Livrable1;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Oiseau extends JPanel{
	private static final long serialVersionUID = 1L;
	private Rectangle r;
	private int x = 0;
	private int y = 0;
	private static int height = 30;
	private static int width = 30;
	
	public Oiseau(int x, int y){
		this.x = x;
		this.y = y;
		r = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getRect(){
		return this.r;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		this.r.setBounds(x, y, r.width, r.height);
	}
	
	public void afficher(Graphics g){
		g.drawOval(x, y, height, width);
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, height, width);
		g.setColor(Color.YELLOW);
		g.fillArc(x+30, y-1, width, height, 160, 30);
		g.setColor(Color.BLACK);
		g.drawOval(x+8, y+10, width-10, height-20);
		g.setColor(Color.WHITE);
		g.fillOval(x+8, y+10, width-10, height-20);
		g.setColor(Color.BLACK);
		g.fillOval(x+13, y+10, width-20, height-20);
	}
}
