package com.thehorriblehacker.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.thehorriblehacker.main.Main;

public class Camera {

	public Vector3f position;
	public Matrix4f projection;
	
	public Camera(float x, float y, float z) {
		position = new Vector3f(x, y, z);
		projection = new Matrix4f().ortho2D(-Main.WIDTH / 2, Main.WIDTH / 2, -Main.HEIGHT / 2, Main.HEIGHT / 2);
	}
	
	public Matrix4f projection() {
		Matrix4f result = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(position);
		
		result = projection.mul(result, pos);
		
		return result;
	}
	
	public void setPosition(Vector3f pos) {
		this.position = pos;
	}
	
}