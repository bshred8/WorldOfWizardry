package com.thehorriblehacker.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Toolkit;

import org.lwjgl.opengl.GL;

import com.thehorriblehacker.graphics.Camera;
import com.thehorriblehacker.graphics.Shader;
import com.thehorriblehacker.states.GameStateManager;

public class Main {
	
	private GameStateManager gsm;
	
	public static long window;
	
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static void main(String[] args) {
		if(!glfwInit()) { // INITIALIZING GLFW
			// TODO: Window pop-up with this message on it when crash
			throw new IllegalStateException("Couldn't initialize GLFW!");
		}
		
		Main main = new Main();
		main.start();
	}
	
	private void start() {
		//CREATING THE WINDOW
		glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
		window = glfwCreateWindow(WIDTH, HEIGHT, "World of Wizardry", 0, 0);
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		gsm = new GameStateManager();
		
		loop();
	}
	
	private void loop() {
		glfwShowWindow(window);
		
		Camera camera = new Camera(0, 0, 0);
		Shader shader = new Shader("shaders/camera.vs", "shaders/camera.fs");
		
		while(!glfwWindowShouldClose(window)) {
			{// INUPT HERE
				
			}
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			{ // RENDERING HERE
				shader.setUniform("projection", camera.projection());
				shader.use();
				
				gsm.renderCurrentState();
				gsm.updateCurrentState();
				gsm.updateButtonsOnState();
			}
			glfwSwapBuffers(window);
		}
		glfwDestroyWindow(window);
		System.exit(0);
	}
	
}