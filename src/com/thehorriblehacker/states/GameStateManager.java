package com.thehorriblehacker.states;

import java.util.ArrayList;

import com.thehorriblehacker.states.main.MainMenu;
import com.thehorriblehacker.states.main.SettingsMenu;

public class GameStateManager {

	public ArrayList<GameState> gameStates = new ArrayList<GameState>();
	public int currentState = 0;
	
	public static final int MAIN_MENU_STATE = 0;
	public static final int SETTINGS_MENU_STATE = 1;
	
	public GameStateManager() {
		gameStates.add(new MainMenu(this));
		gameStates.add(new SettingsMenu(this));
	}
	
	public void renderCurrentState() {
		gameStates.get(currentState).render();
	}
	
	public void updateCurrentState() {
		gameStates.get(currentState).update();
	}
	
	public void updateButtonsOnState() {
		gameStates.get(currentState).updateButtons();
	}
	
}
