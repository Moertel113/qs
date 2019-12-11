package ingame;

public class Player {

	String name;

	int pickups = 0;

	int playerID;
	int shiptype;
	int deaths;
	int controls[] = new int[6];

	public Player(int playerID, String name, int shiptype, int up, int down, int left, int right, int skillOne,int skillTwo) {
		this.shiptype = shiptype;
		this.name = name;
		this.controls[0] = up;
		this.controls[1] = down;
		this.controls[2] = left;
		this.controls[3] = right;
		this.controls[4] = skillOne;
		this.controls[5] = skillTwo;
		this.playerID = playerID;
	}

	public int getDeaths() {
		return deaths;
	}

	public void addToDeaths(int deaths) {
		this.deaths += deaths;
	}

	public int getPlayerID() {
		return playerID;
	}

	public int getShiptype() {
		return shiptype;
	}

	public int[] getControls() {
		return controls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public void setShiptype(int shiptype) {
		this.shiptype = shiptype;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public void setControls(int[] controls) {
		this.controls = controls;
	}

	public void addPickUps() {
		pickups++;
	}

	public int getPickUps() {
		return pickups;
	}

}
