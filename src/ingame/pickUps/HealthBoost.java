package ingame.pickUps;

import ingame.ships.Spaceship;

public class HealthBoost extends Item {

	double healthAmount, missingHealth, healthBoost;

	public HealthBoost(int x_pos, int y_pos) {
		super(x_pos, y_pos);

		healthBoost = 0.3;

		textureURL = "/healthBoost.png";

		loadTexture();
	}

	/*
	 * adds 30% of the maxHealth of ship to its current health but only so much
	 * that the ship is at maxHealth
	 */
	@Override
	public void affect(Spaceship ship) {
		healthAmount = ship.getCurrentHealth() * healthBoost;
		missingHealth = ship.getHealth() - ship.getCurrentHealth();
		if (healthAmount < missingHealth) {
			ship.addHealth(healthAmount);
		} else {
			ship.addHealth(missingHealth);
		}
	}

}
