package Livrable1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventListener implements MouseListener {
	private Jeu j;

	public EventListener(Jeu j) {
		this.j = j;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + "-" + e.getY());
		j.lancerJeu(3);
		j.getPan().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
