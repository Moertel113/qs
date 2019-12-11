package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import helpers.Keys;

import javax.imageio.ImageIO;

public class PauseState extends GameState {

	BufferedImage pauseScreen;

	public PauseState(GameStateManager gsm) {
		super(gsm);

		try {
			pauseScreen = ImageIO.read(getClass().getResourceAsStream("/PauseScreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		handleInput();

	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(pauseScreen,0,0,null);

	}

	@Override
	public void handleInput() {
		if (Keys.isPressed(Keys.ESC)) {
			gsm.resumeGame();
		}

	}

}
