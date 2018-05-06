package com.thehorriblehacker.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class Shader {
	
	private int vertexShader, fragmentShader;
	public int program;
	
	public Shader(String vertexSource, String fragmentSource) {
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(vertexShader, readFile(vertexSource));
		glShaderSource(fragmentShader, readFile(fragmentSource));
		
		glCompileShader(vertexShader);
		glCompileShader(fragmentShader);
		
		if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) != 1) {
			System.err.println(glGetShaderInfoLog(vertexShader));
			System.exit(-1);
		}
		
		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) != 1) {
			System.err.println(glGetShaderInfoLog(fragmentShader));
			System.exit(-1);
		}
		
		program = glCreateProgram();
		
		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		
		glBindAttribLocation(program, 0, "vertices");
		glBindAttribLocation(program, 1, "textures");
		
		glLinkProgram(program);
	}
	
	private String readFile(String source) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(source)));
			String temp;
			while((temp = br.readLine()) != null) {
				result.append(temp + "\n");
			}
			
			br.close();
			br = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.gc();
		
		return result.toString();
	}
	
	public void bindAttribLocation(String attrib, int value) {
		glBindAttribLocation(program, value, attrib);
	}
	
	public void setUniform(String attrib, int value) {
		int location = glGetUniformLocation(program, attrib);
		if(location != 1) {
			glUniform1i(location, value);
		}
	}
	
	public void setUniform(String attrib, Matrix4f val) {
		int location = glGetUniformLocation(program, attrib);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		val.get(buffer);
		if(location != -1) {
			glUniformMatrix4fv(location, false, buffer);
		}
	}
	
	public void use() {
		glUseProgram(program);
	}
	
	public void destroy() {
		glDeleteProgram(program);
	}
	
}