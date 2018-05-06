package com.thehorriblehacker.states.main;

import com.thehorriblehacker.graphics.Shader;
import com.thehorriblehacker.graphics.Texture;
import com.thehorriblehacker.main.Main;
import com.thehorriblehacker.states.GameState;
import com.thehorriblehacker.states.GameStateManager;
import com.thehorriblehacker.ui.UIButton;

public class MainMenu extends GameState {

	public MainMenu(GameStateManager gsm) {
		super(gsm);
		
		componentShader = new Shader("shaders/UI/button.vs", "shaders/UI/button.fs");
		orderedComponentTextures.add(0, new Texture("res/playButton.png"));
		orderedComponentTextures.add(1, new Texture("res/settingsButton.png"));
		orderedComponentTextures.add(2, new Texture("res/creditsButton.png"));
		orderedComponentTextures.add(3, new Texture("res/exitButton.png"));
		{// PLAY BUTTON ADD
			int playButtonWidth = Main.HEIGHT / 4;
			int playButtonHeight = Main.HEIGHT / 4;
			int playButtonX = -playButtonWidth;
			int playButtonY = playButtonHeight;
			components.add(0, new UIButton(playButtonX, playButtonY, playButtonWidth, playButtonHeight));
		}// END PLAY BUTTON ADD
		
		
		{//SETTINGS BUTTON ADD
			int settingsButtonWidth = Main.HEIGHT / 4;
			int settingsButtonHeight = Main.HEIGHT / 4;
			int settingsButtonX = settingsButtonWidth;
			int settingsButtonY = settingsButtonHeight;
			components.add(1, new UIButton(settingsButtonX, settingsButtonY, settingsButtonWidth, settingsButtonHeight));
		}//END SETTINGS BUTTON ADD
		
		{//CREDITS BUTTON ADD
			int creditsButtonWidth = Main.HEIGHT / 4;
			int creditsButtonHeight = Main.HEIGHT / 4;
			int creditsButtonX = -creditsButtonWidth;
			int creditsButtonY = -creditsButtonHeight;
			components.add(2, new UIButton(creditsButtonX, creditsButtonY, creditsButtonWidth, creditsButtonHeight));
		}//END CREDITS BUTTON ADD
		
		{//EXIT BUTTON ADD
			int exitButtonWidth = Main.HEIGHT / 4;
			int exitButtonHeight = Main.HEIGHT / 4;
			int exitButtonX = exitButtonWidth;
			int exitButtonY = -exitButtonHeight;
			components.add(3, new UIButton(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight));
		}//END EXIT BUTTON ADD
		
		buttons.add(0, (UIButton)components.get(0));
		buttons.add(1, (UIButton)components.get(1));
		buttons.add(2, (UIButton)components.get(2));
		buttons.add(3, (UIButton)components.get(3));
	}

	@Override
	public void update() {
		
	}
	
}