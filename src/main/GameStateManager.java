package main;

import java.awt.Graphics2D;

import ingame.PlayState;
import ingame.Player;

public class GameStateManager {
	
	public final static int NUMSTATES = 5;
	
	public final static int MENUSTATE = 0;
	public final static int MENUSELECTIONSTATE = 1;
	public final static int PLAYSTATE = 2;
	public final static int PLAYENDSTATE = 3;
	public final static int PAUSESTATE = 4;
	
	
	
	int previousState;
	int currentState;
	
	GameState[] gameStates;
	
	
	public GameStateManager(){
		
		gameStates = new GameState[NUMSTATES];
		setState(MENUSTATE);
	}

	public void update() {
		gameStates[currentState].update();
	}

	public void draw(Graphics2D g) {
		gameStates[currentState].draw(g);	
	}
	
	public void setState(int state){
		previousState = currentState;
		unload(previousState);
		currentState = state;
		if(state == MENUSTATE){
			gameStates[MENUSTATE] = new MenuState(this);
			gameStates[MENUSTATE].init();
		}else if(state == MENUSELECTIONSTATE){
			gameStates[MENUSELECTIONSTATE] = new PlaySetupState(this);
			gameStates[MENUSELECTIONSTATE].init();
		}
		
		
	}
	
	public void loadPlayState(Player players[], int maxDeaths){
		previousState = currentState;
		unload(previousState);
		currentState = PLAYSTATE;
		gameStates[PLAYSTATE] = new PlayState(this, players, maxDeaths);
		gameStates[PLAYSTATE].init();
	}
	
	public void loadPlayEndState(Player players[]){
		previousState = currentState;
		unload(previousState);
		currentState = PLAYENDSTATE;
		gameStates[PLAYENDSTATE] = new PlayEndState(this, players);
		gameStates[PLAYENDSTATE].init();
	}

	public void unload(int state) {
		gameStates[state] = null;
		
	}
	
	public void resumeGame(){
		previousState = currentState;
		currentState = PLAYSTATE;
		unload(PAUSESTATE);
	}

	public void pauseState() {
		previousState = currentState;
		currentState = PAUSESTATE;
		gameStates[PAUSESTATE] = new PauseState(this);
		gameStates[PAUSESTATE].init();
		
		
	}

}
