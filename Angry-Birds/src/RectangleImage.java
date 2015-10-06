

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class RectangleImage{
	private Image img = null;
	private Rectangle rect = null;

	public RectangleImage(Image img, int x, int y) {
		this.img = img;
		ImageIcon icon = new ImageIcon(img);
		this.rect = new Rectangle(x, y, icon.getIconWidth(),
				icon.getIconHeight());
	}

	public Rectangle getRect() {
		return this.rect;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Image getImg() {
		return this.img;
	}

	public void move(int x, int y) {
		this.rect.setBounds(x, y, rect.width, rect.height);
	}

	public void draw(Graphics g, ImageObserver o) {
		g.drawImage(this.img, this.rect.x, this.rect.y, this.rect.width,
				this.rect.height, o);
	}
}