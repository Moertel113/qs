package ingame.ships;

import java.util.ArrayList;

import helpers.Sound;
import ingame.bullets.Bullet;
import ingame.bullets.GreenBullet;
import ingame.skills.Heal;
import ingame.skills.LaserBeam;
import ingame.skills.Skill;

public class GreenShip extends Spaceship {

	public static int cooldown = 50;
	public static int health = 100;
	public static int maxTemperature = 200;
	public static int bulletHeat = 100;

	public static String URL = "/Spaceship_green.png";
	public static String URL_thrust = "/Spaceship_green_thrust.png";
	
	public static Skill[] skills = {new Heal(), new LaserBeam()};

	public GreenShip(int shipID, ArrayList<Bullet> bullets, int[] controls) {
		super(URL, URL_thrust, shipID, bullets, controls, cooldown, health, maxTemperature, skills);
		
		this.omega = 3;
		this.acceleration = 0.06;
	}

	public void shoot() {
		if (!overheat) {
			if (currentTemperatue > maxTemperature) {
				overheat = true;
			}
			if (cooldownCounter <= 0) {
				currentTemperatue += bulletHeat;
				Sound.play("SHOOT");
				bullets.add(new GreenBullet(position, theta, shipID));
				speed.addToX(Math.cos(theta) * -GreenBullet.getRecoil());
				speed.addToY(Math.sin(theta) * -GreenBullet.getRecoil());
				cooldownCounter = cooldown;
			}
		}
	}
	
	

}
