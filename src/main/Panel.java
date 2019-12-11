package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import helpers.Keys;
import helpers.Sound;
import helpers.Textures;

public class Panel extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	private boolean running;
	private final int FPS = 30;
	private final int TARGET_TIME = 1000 / FPS;

	private BufferedImage image;
	private Graphics2D g;
	GameStateManager gsm;

	long fps = 0;

	public Panel() {
		addKeyListener(this);
		setPreferredSize(new Dimension(SpaceBoot.WIDTH, SpaceBoot.HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	long elapsed;
	long wait;

	public void run() {
		init();
		long start;
		while (running) {

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;
			wait = TARGET_TIME - elapsed / 1000000;
			if (wait < 0)
				wait = TARGET_TIME;
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void init() {
		running = true;
		Sound.init();
		Textures.init();
		image = new BufferedImage(SpaceBoot.WIDTH, SpaceBoot.HEIGHT, 1);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
		// Sound.load("/music.wav", "MUSIC");
		// Sound.loop("MUSIC");
	}

	private void update() {
		gsm.update();
		Keys.update();
		calculateFPS();
	}

	private void draw() {
		// g.clearRect(0, 0, SpaceBoot.WIDTH, SpaceBoot.HEIGHT);
		gsm.draw(g);
		g.setColor(Color.WHITE);
		if (fps < 25)
			g.setColor(Color.RED);
		g.drawString("FPS: " + fps, 10, 15);
	}

	int counter = 0;

	private long calculateFPS() {
		counter++;
		if (counter >= 5) {
			fps = Math.round(1000 / (elapsed * 0.000001 + wait));
			counter = 0;
		}
		return fps;
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Keys.keySet(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keys.keySet(e.getKeyCode(), false);

	}

}