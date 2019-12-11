package ingame.pickUps;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Vector;
import ingame.ships.Spaceship;

public abstract class PickUp {

	BufferedImage texture;

	Vector position;

	String textureURL;

	double radius;
	int lifeTime, ownerID;

	boolean isPickedUp = false;
	boolean isActive = false;

	/*
	 * Sets the position and loads the texture of PickUp.
	 */
	public PickUp(int x_pos, int y_pos, int lifeTime) {
		position = new Vector(x_pos, y_pos);

		this.lifeTime = lifeTime;
	}

	/*
	 * Decreases the lifetime until the PickUp is dead
	 */
	public void update() {
		if (isAlive()) {
			lifeTime--;
		} 

	}

	/*
	 * If pickUp is not already picked up: Checks whether the PickUp is close
	 * enough to get picked up by calculating the distance between the center of
	 * the PickUp and the given position. Takes the size of the PickUp into
	 * consideration by this.radius and the size of the Ship by minDistance.
	 * ALso assigns the OwnerID.
	 */
	public boolean getsPickedUp(Spaceship ship, double minDistance) {
		if (!isPickedUp) {
			if (this.position.getDistanceTo(ship.getPos()) < this.radius + minDistance) {
				lifeTime = 0;
				isPickedUp = true;
				ownerID = ship.getShipID();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * Returns whether the PickUp is still alive, meaning it is not picked up
	 * and still on Screen(available for pick up)
	 */
	public boolean isAlive() {
		if (lifeTime > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isActive() {
		return false;
	}

	/*
	 * Returns whether the PickUp is already picked up.
	 */
	public boolean isPickedUp() {
		return isPickedUp;
	}

	/*
	 * Loads the texture from textureURL.
	 */
	public void loadTexture() {
		try {
			texture = ImageIO.read(getClass().getResource(textureURL));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void affect(Spaceship ship) {
	};

	public void unAffect(Spaceship ship) {
	};

	/*
	 * Draws the texture at this.position.
	 */
	public void draw(Graphics2D g) {

		g.drawImage(texture, (int) this.position.getX(), (int) this.position.getY(), null);
	}
	
	public int getOwnerID(){
		return ownerID;
	}
}
