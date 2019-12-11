package main;

import java.awt.Color;
import java.awt.Graphics2D;

import helpers.Keys;
import ingame.Player;

public class PlayEndState extends GameState {

	Player players[];
	GameStateManager gsm;

	public PlayEndState(GameStateManager gsm, Player players[]) {
		super(gsm);
		this.gsm = gsm;
		this.players = players;
	}

	@Override
	public void init() {

	}

	@Override
	public void update() {
		handleInput();
	}

	private Player calculateWinner() {
		Player winner = new Player(-1, "Error finding Winner", 0, 0, 0, 0, 0, 0, 0);
		int lowestdeaths = 100;
		for (Player p : players) {
			if (p.getDeaths() < lowestdeaths) {
				lowestdeaths = p.getDeaths();
				winner = p;
			}
		}

		return winner;

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawString("Press Enter to go back to Title Screen", 200, 200);
		g.drawString(calculateWinner().getName() + " has won the Game", 200, 250);
	}

	@Override
	public void handleInput() {
		if (Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameStateManager.MENUSTATE);
		}

	}

}
