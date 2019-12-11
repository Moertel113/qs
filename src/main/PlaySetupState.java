package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Keys;
import helpers.Sound;
import ingame.Player;
import ingame.ships.BlueShip;
import ingame.ships.GreenShip;
import ingame.ships.RedShip;
import ingame.ships.Spaceship;

public class PlaySetupState extends GameState {

	Player player[];
	int numplayer;
	int lives;

	static int BUTTONS = 4;
	static int NUMBERPLAYER = 0;
	static int SHIP = 1;
	static int LIVES = 2;
	static int GO = 3;

	int selectedButton;

	BufferedImage background;
	BufferedImage redShip;
	BufferedImage greenShip;
	BufferedImage blueShip;

	public PlaySetupState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {

		try {
			background = ImageIO.read(getClass().getResourceAsStream("/submenu.png"));
			redShip = ImageIO.read(getClass().getResourceAsStream(RedShip.URL));
			greenShip = ImageIO.read(getClass().getResourceAsStream(GreenShip.URL));
			blueShip = ImageIO.read(getClass().getResourceAsStream(BlueShip.URL));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sound.load("/enter.wav", "ENTER");
		Sound.load("/select.wav", "SELECT");

		selectedButton = 0;
		lives = 3;
		numplayer = 2;

		player = new Player[numplayer];
		player[0] = new Player(0, "Right Player", Spaceship.REDSHIP, Keys.I, Keys.K, Keys.J, Keys.L, Keys.U, Keys.O);
		player[1] = new Player(1, "Left Player", Spaceship.GREENSHIP, Keys.W, Keys.S, Keys.A, Keys.D, Keys.Q, Keys.E);
	}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		g.setColor(new Color(233, 54, 4));
		drawShips(g);
        drawLives(g);

		g.fillOval((int) (getX() * SpaceBoot.WIDTH - 20), (int) (getY() * SpaceBoot.HEIGHT - 6), 12, 12);
	}

	private void drawShips(Graphics2D g) {

		if (player[1].getShiptype() == Spaceship.REDSHIP) {
			g.drawImage(redShip, (int) (0.234 * SpaceBoot.WIDTH), (int) (0.46 * SpaceBoot.HEIGHT), (int) (0.1375 * SpaceBoot.WIDTH), (int) (0.1833 * SpaceBoot.HEIGHT), null);
		}
		if (player[1].getShiptype() == Spaceship.BLUESHIP) {
			g.drawImage(blueShip, (int) (0.234 * SpaceBoot.WIDTH), (int) (0.46 * SpaceBoot.HEIGHT), (int) (0.1375 * SpaceBoot.WIDTH), (int) (0.1833 * SpaceBoot.HEIGHT), null);
		}
		if (player[1].getShiptype() == Spaceship.GREENSHIP) {
			g.drawImage(greenShip, (int) (0.234 * SpaceBoot.WIDTH), (int) (0.46 * SpaceBoot.HEIGHT), (int) (0.1375 * SpaceBoot.WIDTH), (int) (0.1833 * SpaceBoot.HEIGHT), null);

		}
		if (player[0].getShiptype() == Spaceship.REDSHIP) {
			g.drawImage(redShip, (int) (0.6285 * SpaceBoot.WIDTH), (int) (0.46 * SpaceBoot.HEIGHT), (int) (0.1375 * SpaceBoot.WIDTH), (int) (0.1833 * SpaceBoot.HEIGHT), null);
		}
		if (player[0].getShiptype() == Spaceship.BLUESHIP) {
			g.drawImage(blueShip, (int) (0.6285 * SpaceBoot.WIDTH), (int) (0.46 * SpaceBoot.HEIGHT), (int) (0.1375 * SpaceBoot.WIDTH), (int) (0.1833 * SpaceBoot.HEIGHT), null);
		}
		if (player[0].getShiptype() == Spaceship.GREENSHIP) {
			g.drawImage(greenShip, (int) (0.6285 * SpaceBoot.WIDTH), (int) (0.46 * SpaceBoot.HEIGHT), (int) (0.1375 * SpaceBoot.WIDTH), (int) (0.1833 * SpaceBoot.HEIGHT), null);

		}
	}

	private void drawLives(Graphics2D g) {
		g.setFont( new Font("ArialMT",Font.BOLD, 34));
		g.drawString(String.valueOf(lives), (int) (0.57 * SpaceBoot.WIDTH), (int) (0.718 * SpaceBoot.HEIGHT));
		g.setFont( new Font("ArialMT",Font.BOLD, 12));
	}

	private double getX() {
		if (selectedButton == NUMBERPLAYER)
			return 0.31125;
		if (selectedButton == SHIP)
			return 0.31875;
		if (selectedButton == LIVES)
			return 0.4325;
		if (selectedButton == GO)
			return 0.47375;
		return 0;
	}

	private double getY() {
		if (selectedButton == NUMBERPLAYER)
			return 0.1387;
		if (selectedButton == SHIP)
			return 0.3367;
		if (selectedButton == LIVES)
			return 0.693;
		if (selectedButton == GO)
			return 0.902;
		return 0;
	}

	@Override
	public void handleInput() {

		if (Keys.isPressed(Keys.UP) && selectedButton > 0) {
			selectedButton--;
			Sound.play("SELECT");
		}
		if (Keys.isPressed(Keys.DOWN) && selectedButton < BUTTONS) {
			selectedButton++;
			Sound.play("SELECT");
		}

		if (Keys.isPressed(Keys.ENTER) && selectedButton == GO) {
			gsm.loadPlayState(player, lives);
			Sound.play("ENTER");
		}

		if (Keys.isPressed(Keys.LEFT)) {
			Sound.play("SELECT");
			if (selectedButton == SHIP && player[0].getShiptype() > 0) {
				player[0].setShiptype(player[0].getShiptype() - 1);
			}
		}
		if (Keys.isPressed(Keys.RIGHT)) {
			Sound.play("SELECT");
			if (selectedButton == SHIP && player[0].getShiptype() < Spaceship.SHIPSNUM - 1) {
				player[0].setShiptype(player[0].getShiptype() + 1);
			}
		}

		if (Keys.isPressed(Keys.A)) {
			Sound.play("SELECT");
			if (selectedButton == SHIP && player[1].getShiptype() > 0) {
				player[1].setShiptype(player[1].getShiptype() - 1);
			}
		}
		if (Keys.isPressed(Keys.D)) {
			Sound.play("SELECT");
			if (selectedButton == SHIP && player[1].getShiptype() < Spaceship.SHIPSNUM - 1) {
				player[1].setShiptype(player[1].getShiptype() + 1);
			}
		}

		if (Keys.isPressed(Keys.LEFT)) {
			Sound.play("SELECT");
			if (selectedButton == LIVES && lives > 1) {
				lives--;
			}
		}
		if (Keys.isPressed(Keys.RIGHT)) {
			Sound.play("SELECT");
			if (selectedButton == LIVES && lives < 10) {
				lives++;
			}
		}
	}

}
