package com.thehorriblehacker.states;

import java.util.ArrayList;

import com.thehorriblehacker.graphics.Shader;
import com.thehorriblehacker.graphics.Texture;
import com.thehorriblehacker.ui.UIButton;
import com.thehorriblehacker.ui.UIComponent;

public abstract class GameState {

	protected GameStateManager gsm;
	
	protected ArrayList<UIComponent> components = new ArrayList<UIComponent>();
	protected ArrayList<Texture> orderedComponentTextures = new ArrayList<Texture>();
	protected Shader componentShader;
	
	protected ArrayList<UIButton> buttons = new ArrayList<UIButton>();
	protected int[] orderedButtonChangeStates;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void render() {
		for(int i=0; i < components.size(); i++) {
			components.get(i).render(componentShader, orderedComponentTextures.get(i));
		}
	}
	public abstract void update();
	
	protected void updateButtons() {
		for(int i=0; i < buttons.size(); i++) {
			if(buttons.get(i).update() == true) {
				buttons.get(i).action(gsm, orderedButtonChangeStates[i]);
			}
		}
	}
	
}