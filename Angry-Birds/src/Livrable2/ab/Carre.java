package Livrable2.ab;

import java.util.Random;

import Livrable2.controller.ControllerObstacle;
import Livrable2.model.ModelObstacle;
import Livrable2.view.VueObstacle;

public class Carre implements Obstacle {
	
	Random r = new Random();

	@Override
	public void creation() {
		// TODO Auto-generated method stub
		ModelObstacle modelObs = new ModelObstacle(r.nextInt(840 - 740 + 1) + 740, r.nextInt(400 - 60 + 1) + 60,false);
		ControllerObstacle controllObs = new ControllerObstacle();
		VueObstacle vueObs = new VueObstacle(modelObs, controllObs);
		modelObs.addObserver(vueObs);
		Jeu.obstacles.add(vueObs);
	}

}
