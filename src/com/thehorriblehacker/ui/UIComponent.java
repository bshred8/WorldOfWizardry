package com.thehorriblehacker.ui;

import com.thehorriblehacker.graphics.Model;
import com.thehorriblehacker.graphics.Shader;
import com.thehorriblehacker.graphics.Texture;

public abstract class UIComponent {

	protected float x, y, z=0, width, height;
	protected Model model;
	
	public UIComponent(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(Shader shader, Texture texture) {
		shader.setUniform("sampler", 0);
		shader.use();
		texture.bind(0);
		
		float[] vertices = {
			x - width, y + height, z,
			x + width, y + height, z,
			x + width, y - height, z,
			x - width, y - height, z
		};
		
		int[] indices = {
			0, 1, 2,
			2, 3, 0
		};
		
		float[] tex_coords = {
			0, 0,
			1, 0,
			1, 1,
			0, 1
		};
		model = new Model(vertices, indices, tex_coords);
		model.render();
	}
	
}