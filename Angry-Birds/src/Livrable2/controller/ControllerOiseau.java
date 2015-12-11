package Livrable2.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import Livrable2.ab.Jeu;
import Livrable2.model.ModelOiseau;
import Livrable2.view.VueOiseau;

public class ControllerOiseau extends Controller {
	private Jeu j;

	public ControllerOiseau(ModelOiseau modelOiseau) {
		this.j = modelOiseau.getJeu();
	}

	@Override
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

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		j.setP1(new Point(e.getX(), e.getY()));
		j.setGo(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		VueOiseau o = j.getOiseau();
		o.move(e.getX(), e.getY());
		System.out.println(o.getModel().getCo());
		if (e.getX() < 20)
			o.move(20, o.getY());
		if (e.getX() > 120)
			o.move(120, o.getY());
		if (e.getY() > 390)
			o.move(o.getX(), 390);
		if (e.getY() < 345)
			o.move(o.getX(), 345);

		j.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}


}