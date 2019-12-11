package ingame.pickUps;

import ingame.ships.Spaceship;

public class Shield extends Item {

	public Shield(int x_pos, int y_pos) {
		super(x_pos, y_pos);
		
		textureURL = "/shield.png";
		
		loadTexture();
	}
	
	/*
	 * sets shield boolean of ship to true 
	 */
	@Override
	public void affect(Spaceship ship){
		ship.haveShield();
	}
	
}
