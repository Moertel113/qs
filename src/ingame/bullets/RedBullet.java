package ingame.bullets;
import helpers.Vector;

public class RedBullet extends Bullet {


	static double speedABS = 30;
	private static double recoil = 0.05;
	static double damage = 3;
	static String URL = "/red_bullet.png";

	public RedBullet(Vector position, double theta, int ownerID) {
		super(position, theta, ownerID, speedABS, URL, recoil, damage);
		
	}

	public static double getRecoil() {
		return recoil;
	}
}
