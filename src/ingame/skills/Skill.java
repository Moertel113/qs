package ingame.skills;

import java.awt.Graphics2D;

import ingame.ships.Spaceship;

public abstract class Skill {

	int cooldownTime, loadingTime, currentCooldownTime;
	boolean ready;

	public Skill(int cooldownTime, int loadingTime) {
		this.cooldownTime = cooldownTime;
		this.loadingTime = loadingTime;

		ready = false;
		currentCooldownTime = 0;
	}

	/*
	 * Updates cooldownTime and switches ready to true when cooldown is
	 * finished. Also generates Error when currentCooldownTime sinks below zero
	 */
	public void update() {
		if (currentCooldownTime > 0) {
			currentCooldownTime--;
		} else if (currentCooldownTime == 0) {
			ready = true;
		} else {
			System.out.println("ERROR: negative cooldown time: " + currentCooldownTime);
		}
	}

	/*
	 * activates skill when ready and resets cooldown also sets skill as not
	 * ready.
	 */
	public void trySkill(Spaceship ship) {
		if (ready) {
			activateSkill(ship);
			reset();
		}
	}

	public void activateSkill(Spaceship ship) {

	}

	/*
	 * resets the necessary values.
	 */
	public void reset() {
		ready = false;
		currentCooldownTime = cooldownTime;
	}

	public void draw(Graphics2D g) {

	}
}
