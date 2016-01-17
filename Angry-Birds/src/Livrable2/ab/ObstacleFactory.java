package Livrable2.ab;

public class ObstacleFactory {

	public Obstacle getObstacleType(String obsType) {
		if (obsType == null)
			return null;
		if (obsType.equalsIgnoreCase("CARRE")) {
			return new Carre();

		} else if (obsType.equalsIgnoreCase("ROND")) {
			return new Rond();

		}

		return null;

	}

}
