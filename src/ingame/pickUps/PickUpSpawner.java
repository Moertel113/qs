package ingame.pickUps;

import java.util.Random;

import main.SpaceBoot;

public class PickUpSpawner {

	Random r;
	int rnd;

	public PickUpSpawner() {
		r = new Random();
	}

	/*
	 * Spawns random Pickup with on a radnom position but at least 100p form the
	 * borders away.
	 */
	public PickUp spawnRandomPickUp() {
		rnd = r.nextInt(5);
		switch (rnd) {
		case 0:
			return new Shield(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
		case 1:
			return new HealthBoost(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
		case 2:
			return new HandlingBoost(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
		case 3:
		case 4:
			return new SpeedBoost(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
		default:
			System.out.println("PickUpSpawner spawns Nothing");
			return null;
		}
	}

	/*
	 * Spawns specific PickUp at a random position but at least 100p from the
	 * borders away.
	 */
	public Item spawnHealthBoost() {
		return new HealthBoost(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
	}

	public Buff spawnSpeedBoost() {
		return new SpeedBoost(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
	}

	public Buff spawnHandlingBoost() {
		return new HandlingBoost(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
	}

	public Item spawnItem() {
		return new Item(r.nextInt(SpaceBoot.WIDTH - 200) + 100, r.nextInt(SpaceBoot.HEIGHT - 200) + 100);
	}
}
