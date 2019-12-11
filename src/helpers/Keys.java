package helpers;

import java.awt.event.KeyEvent;

public class Keys {

	public static final int NUM_KEYS = 18;

	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];

	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;

	public static int W = 4;
	public static int A = 5;
	public static int S = 6;
	public static int D = 7;
	public static int Q = 8;
	public static int E = 9;

	public static int I = 10;
	public static int J = 11;
	public static int K = 12;
	public static int L = 13;
	public static int U = 14;
	public static int O = 15;

	public static int ENTER = 16;
	public static int ESC = 17;

	public static void keySet(int i, boolean b) {
		if (i == KeyEvent.VK_UP)
			keyState[UP] = b;
		else if (i == KeyEvent.VK_LEFT)
			keyState[LEFT] = b;
		else if (i == KeyEvent.VK_DOWN)
			keyState[DOWN] = b;
		else if (i == KeyEvent.VK_RIGHT)
			keyState[RIGHT] = b;
		else if (i == KeyEvent.VK_W)
			keyState[W] = b;
		else if (i == KeyEvent.VK_A)
			keyState[A] = b;
		else if (i == KeyEvent.VK_S)
			keyState[S] = b;
		else if (i == KeyEvent.VK_D)
			keyState[D] = b;
		else if (i == KeyEvent.VK_Q)
			keyState[Q] = b;
		else if (i == KeyEvent.VK_E)
			keyState[E] = b;
		else if (i == KeyEvent.VK_I)
			keyState[I] = b;
		else if (i == KeyEvent.VK_J)
			keyState[J] = b;
		else if (i == KeyEvent.VK_K)
			keyState[K] = b;
		else if (i == KeyEvent.VK_L)
			keyState[L] = b;
		else if (i == KeyEvent.VK_U)
			keyState[U] = b;
		else if (i == KeyEvent.VK_O)
			keyState[O] = b;
		else if (i == KeyEvent.VK_ENTER)
			keyState[ENTER] = b;
		else if (i == KeyEvent.VK_ESCAPE)
			keyState[ESC] = b;
	}

	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}

	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}

	public static boolean isDown(int i) {
		return keyState[i];
	}

	public static boolean anyKeyDown() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i])
				return true;
		}
		return false;
	}

	public static boolean anyKeyPress() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i] && !prevKeyState[i])
				return true;
		}
		return false;
	}

}