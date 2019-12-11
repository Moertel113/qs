package ingame.bullets;
import helpers.Vector;

public class GreenBullet extends Bullet{
	
	static double speedABS = 14;
	static double recoil = 2;
	static double damage = 13;
	static String URL = "/green_bullet.png";

	public GreenBullet(Vector position, double theta, int ownerID) {
		super(position, theta, ownerID, speedABS, URL, recoil, damage);
		
	}
	
	public static double getRecoil() {
		return recoil;
	}
}
