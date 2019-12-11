package ingame.bullets;
import helpers.Vector;

public class PurpleBullet extends Bullet{
	
	static double speedABS = 10;
	static double recoil = 0.05;
	static double damage = 5;
	static String URL = "/purple_bullet.png";

	public PurpleBullet(Vector position, double theta, int ownerID) {
		super(position, theta, ownerID, speedABS, URL, recoil, damage);
		
	}
	
	public static double getRecoil() {
		return recoil;
	}
}