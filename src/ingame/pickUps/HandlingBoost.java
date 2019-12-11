package ingame.pickUps;

import ingame.ships.Spaceship;

public class HandlingBoost extends Buff {

	static int activeTime = 300;

	public double handlingBoost;

	/*
	 * Instantiate activeTime and pass it to superclass via super constructor.
	 */
	public HandlingBoost(int x_pos, int y_pos) {
		super(x_pos, y_pos, activeTime);

		handlingBoost = 1.4;

		textureURL = "/handlingBoost.png";

		loadTexture();
	}

	/*
	 * Boosts ships turning angle per second by handlingBoost amount.
	 */
	@Override
	public void affect(Spaceship ship) {
		ship.setOmega(ship.getOmega() * handlingBoost);
	}

	/*
	 * Resets ships turning angle per second by handlingBoost amount.
	 */
	@Override
	public void unAffect(Spaceship ship) {
		ship.setOmega(ship.getOmega() / handlingBoost);
	}
}