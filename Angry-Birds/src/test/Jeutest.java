package test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import Livrable1.Jeu;
import Livrable1.Obstacle;
import Livrable1.Oiseau;


public class Jeutest {
	private static Jeu j;
	
	@Test
	public final void nblancer() {
		j.lancerJeu(2);
		Assert.assertEquals(2,j.getI());
	}
	
	@Test
	public final void coord_obstacle() {
		Obstacle o = new Obstacle(0, 0);
		Assert.assertEquals(0,o.getPosX());
		Assert.assertEquals(0,o.getPosY());
	}
	
	@Test
	public final void coord_oiseau() {
		Oiseau o = new Oiseau(0, 0);
		Assert.assertEquals(0,o.getX());
		Assert.assertEquals(0,o.getY());
	}
	
	@BeforeClass
	public final static void init(){
		j = new Jeu("Test");
		j.configFrame();
	}
	

}
