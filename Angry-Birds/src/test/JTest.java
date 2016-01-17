package test;

import org.junit.Test;

import Livrable2.ab.Jeu;
import Livrable2.controller.ControllerObstacle;
import Livrable2.model.ModelObstacle;
import Livrable2.view.VueObstacle;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class JTest {
	private static Jeu j;

	@SuppressWarnings("deprecation")
	@Test
	public void vieObstacle() {
		ModelObstacle m = new ModelObstacle(5, 5);
		ControllerObstacle c = new ControllerObstacle();
		VueObstacle o = new VueObstacle(m, c);
		o.setVie(50);
		Assert.assertEquals(50, o.getVie());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void placementXObstacle() {
		ModelObstacle m = new ModelObstacle(5, 2);
		ControllerObstacle c = new ControllerObstacle();
		VueObstacle o = new VueObstacle(m, c);
		Assert.assertEquals(5, o.getX());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void placementYObstacle() {
		ModelObstacle m = new ModelObstacle(5, 2);
		ControllerObstacle c = new ControllerObstacle();
		VueObstacle o = new VueObstacle(m, c);
		Assert.assertEquals(2, o.getY());
	}
}
