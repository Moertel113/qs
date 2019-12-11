package ingame.pickUps;

public class Buff extends PickUp {

	static int lifeTime = 600;

	int activeTime;

	/*
	 * Instantiate lifeTime and pass it to superclass via super constructor.
	 */
	public Buff(int x_pos, int y_pos, int activeTime) {
		super(x_pos, y_pos, lifeTime);

		this.activeTime = activeTime;

	}

	/*
	 * Overrides update method to introduce activeTimes for Buffs.
	 */
	@Override
	public void update() {
		if (isAlive()) {
			lifeTime--;
		} else if (isPickedUp && isActive()) {
			activeTime--;
		}

	}

	/*
	 * This method shows if the buff is Active, which means that a player picked
	 * it up and it is affecting him.
	 */
	@Override
	public boolean isActive() {
		if (activeTime > 0 && !isAlive()) {
			return true;
		} else {
			return false;
		}
	}
}
