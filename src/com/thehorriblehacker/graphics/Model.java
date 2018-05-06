package com.thehorriblehacker.graphics;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Model {

	private int i_id, v_id, t_id, draw_count;
	
	public Model(float[] vertices, int[] indices, float[] tex_coords) {
		draw_count = indices.length;
		
		v_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		FloatBuffer vbuffer = BufferUtils.createFloatBuffer(vertices.length);
		vbuffer.put(vertices);
		vbuffer.flip();
		glBufferData(GL_ARRAY_BUFFER, vbuffer, GL_STATIC_DRAW);
		
		i_id = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		IntBuffer ibuffer = BufferUtils.createIntBuffer(indices.length);
		ibuffer.put(indices);
		ibuffer.flip();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, ibuffer, GL_STATIC_DRAW);
		
		t_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		FloatBuffer texBuffer = BufferUtils.createFloatBuffer(tex_coords.length);
		texBuffer.put(tex_coords);
		texBuffer.flip();
		glBufferData(GL_ARRAY_BUFFER, texBuffer, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void render() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		{
			glBindBuffer(GL_ARRAY_BUFFER, v_id);
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

			glBindBuffer(GL_ARRAY_BUFFER, t_id);
			glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
			
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
			glDrawElements(GL_TRIANGLES, draw_count, GL_UNSIGNED_INT, 0);
			
			glBindBuffer(GL_ARRAY_BUFFER, 0);
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		}
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	}
	
}