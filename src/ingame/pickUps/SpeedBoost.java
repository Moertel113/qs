package ingame.pickUps;

import ingame.ships.Spaceship;

public class SpeedBoost extends Buff {

	static int activeTime = 20;
	
	public double speedBoost;
 
	/*
	 * Instantiate activeTime and pass it to superclass via super constructor.
	 */
	public SpeedBoost(int x_pos, int y_pos) {
		super(x_pos, y_pos, activeTime);
		speedBoost = 3;
		

		textureURL = "/speedBoost.png";

		loadTexture();
	}

	/*
	 * Boosts Ships acceleration by the speedBoost amount.
	 */
	@Override
	public void affect(Spaceship ship) {
		ship.setAcceleration(ship.getAcceleration() * speedBoost);
	}

	/*
	 * Resets Ships acceleration by the speedBoost amount.
	 */
	@Override
	public void unAffect(Spaceship ship) {
		ship.setAcceleration(ship.getAcceleration() / speedBoost);
	}

}
