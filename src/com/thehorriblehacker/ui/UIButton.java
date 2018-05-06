package com.thehorriblehacker.ui;

import static org.lwjgl.glfw.GLFW.*;

import java.awt.MouseInfo;

import com.thehorriblehacker.main.Main;
import com.thehorriblehacker.states.GameStateManager;

public class UIButton extends UIComponent {

	private int mouseX = 0, mouseY = 0;
	
	public UIButton(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public boolean update() {
		MouseInfo.getPointerInfo().getLocation().x = mouseX;
		MouseInfo.getPointerInfo().getLocation().y = mouseY;
		
		if(mouseX >= x - width && mouseX <= x + width
				&& mouseY <= y + height && mouseY >= y - height &&
				(glfwGetMouseButton(Main.window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS)) {
			return true;
		}
		return false;
	}
	
	public void action(GameStateManager gsm, int state) {
		gsm.currentState = state;
	}
	
}