package ingame.ships;

import java.util.ArrayList;

import helpers.Sound;
import ingame.bullets.Bullet;
import ingame.bullets.PurpleBullet;
import ingame.skills.Heal;
import ingame.skills.LaserBeam;
import ingame.skills.Skill;

public class BlueShip extends Spaceship {

	public static int cooldown = 20;
	public static int health = 50;
	public static int maxTemperature = 200;
	public static int bulletHeat = 70;

	public static String URL = "/spaceship_blue.png";
	public static String URL_thrust = "/spaceship_blue_thrust.png";
	
	public static Skill[] skills = {new Heal(), new LaserBeam()};

	public BlueShip(int shipID, ArrayList<Bullet> bullets, int[] controls) {
		super(URL, URL_thrust, shipID, bullets, controls, cooldown, health, maxTemperature, skills);

		this.omega = 3;
		this.acceleration = 0.1;
	}

	public void shoot() {
		if (!overheat) {
			if (currentTemperatue > maxTemperature) {
				overheat = true;
			}
			if (cooldownCounter <= 0) {
				currentTemperatue += bulletHeat;
				Sound.play("SHORTSHOOT");
				bullets.add(new PurpleBullet(position, theta, shipID));
				bullets.add(new PurpleBullet(position, theta + Math.PI / 20, shipID));
				bullets.add(new PurpleBullet(position, theta - Math.PI / 20, shipID));
				speed.addToX(Math.cos(theta) * -PurpleBullet.getRecoil());
				speed.addToY(Math.sin(theta) * -PurpleBullet.getRecoil());
				cooldownCounter = cooldown;
			}
		}
	}
}