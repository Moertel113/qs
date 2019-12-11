package main;

import javax.swing.*;

public class SpaceBoot extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8591427253004438443L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	
	public static void main(String[] args) {
		new SpaceBoot();
	}

	public SpaceBoot(){
		setTitle("Quantum Space v2.3.3");
		
		Panel game = new Panel();
		Thread th = new Thread(game);
		th.start();
		
		setResizable(false);
		getContentPane().add(game);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	
	}

}
