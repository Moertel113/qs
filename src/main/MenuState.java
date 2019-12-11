package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Keys;
import helpers.Sound;

public class MenuState extends GameState {

	GameStateManager gsm;

	BufferedImage background;
	String texture = "/menu.png";

	static int PLAY = 0;
	static int QUIT = 1;

	static int BUTTONS = 2;

	int selectedButton;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.gsm = gsm;
	}

	@Override
	public void init() {
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream(texture));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		selectedButton = PLAY;
		Sound.load("/enter.wav", "ENTER");
		Sound.load("/select.wav", "SELECT");

	}

	@Override
	public void update() {
		handleInput();

	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, SpaceBoot.WIDTH, SpaceBoot.HEIGHT, null);
		g.setColor(new Color(233, 54, 4));
		g.fillOval(SpaceBoot.WIDTH * 670 / 1600, (int) (SpaceBoot.HEIGHT * yCoord()) - 6, 12, 12);
	}

	@Override
	public void handleInput() {
		if (Keys.isPressed(Keys.UP)) {
			selectedButton = Math.abs((selectedButton - 1) % BUTTONS);
			Sound.play("SELECT");
		}
		if (Keys.isPressed(Keys.DOWN)) {
			selectedButton = (selectedButton + 1) % BUTTONS;
			Sound.play("SELECT");
		}
		if (Keys.isPressed(Keys.ENTER)) {
			Sound.play("ENTER");
			if (selectedButton == PLAY) {
				gsm.setState(GameStateManager.MENUSELECTIONSTATE);
			}
			if (selectedButton == QUIT) {
				System.exit(0);
			}
		}

	}

	private double yCoord() {
		double d = 0;
		if (selectedButton == PLAY) {
			d = 0.475;
		} else if (selectedButton == QUIT) {
			d = 0.63;
		}
		return d;

	}
}
