package ingame.ships;

import java.util.ArrayList;

import helpers.Sound;
import helpers.Vector;
import ingame.bullets.Bullet;
import ingame.bullets.RedBullet;
import ingame.skills.Heal;
import ingame.skills.LaserBeam;
import ingame.skills.Skill;

public class RedShip extends Spaceship {

	public static int cooldown = 2;
	public static int health = 50;
	public static int maxTemperature = 200;
	public static int bulletHeat = 25;

	public static String URL = "/Spaceship_red.png";
	public static String URL_thrust = "/Spaceship_red_thrust.png";
	
	public static Skill[] skills = {new Heal(), new LaserBeam()};

	public RedShip(int shipID, ArrayList<Bullet> bullets, int[] controls) {
		super(URL, URL_thrust, shipID, bullets, controls, cooldown, health, maxTemperature, skills);
		
		this.omega = 4.5;
		this.acceleration = 0.16;
	}

	public void shoot() {
		if (!overheat) {

			if (currentTemperatue > maxTemperature) {
				overheat = true;
			}
			if (cooldownCounter <= 0) {
				currentTemperatue += bulletHeat;
				Sound.play("SHORTSHOOT");
				bullets.add(new RedBullet(new Vector(position.getX() + Math.cos(theta + Math.PI / 2) * 10, position.getY() + Math.sin(theta + Math.PI / 2) * 10), theta, shipID));
				bullets.add(new RedBullet(new Vector(position.getX() - Math.cos(theta + Math.PI / 2) * 10, position.getY() - Math.sin(theta + Math.PI / 2) * 10), theta, shipID));
				speed.addToX(Math.cos(theta) * -RedBullet.getRecoil());
				speed.addToY(Math.sin(theta) * -RedBullet.getRecoil());
				cooldownCounter = cooldown;
			}
		}
	}

}
