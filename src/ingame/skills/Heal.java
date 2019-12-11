package ingame.skills;

import ingame.ships.Spaceship;

public class Heal extends Skill {

	static int cooldownTime = 300;
	static int loadingTime = 100;
	double missingHealth, healingPower;

	public Heal() {
		super(cooldownTime, loadingTime);
		healingPower = 10;
	}

	/*
	 * Heals by healing power but not more than the missing health.
	 */
	@Override
	public void activateSkill(Spaceship ship) {
		missingHealth = ship.getHealth() - ship.getCurrentHealth();
		if (healingPower < missingHealth) {
			ship.addHealth(healingPower);
		} else {
			ship.addHealth(missingHealth);
		}
	}
}
