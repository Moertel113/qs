package ingame.pickUps;

import ingame.ships.Spaceship;

public class Item extends PickUp {

	static int lifeTime = 600;

	/*
	 * Instantiate lifeTime and pass it to superclass via super constructor.
	 */
	public Item(int x_pos, int y_pos) {
		super(x_pos, y_pos, lifeTime);

	}

	@Override
	public void affect(Spaceship ship) {

	}

}
