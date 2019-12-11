package ingame.skills;

import ingame.ships.Spaceship;

public class LaserBeam extends Skill{

	static int cooldownTime = 100;
	static int loadingTime = 100;
	
	public LaserBeam(){
		super(cooldownTime,loadingTime);
	}
	
	//TODO write this class ;D
	@Override
	public void activateSkill(Spaceship ship) {
		
		
	}
}
