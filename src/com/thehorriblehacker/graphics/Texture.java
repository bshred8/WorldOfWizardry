package com.thehorriblehacker.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {

private int id, width, height; 
	
	public Texture(String filename) {
		try {
			BufferedImage image = ImageIO.read(new File(filename));
			width = image.getWidth();
			height = image.getHeight();
			
			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
			
			int[] pixels_raw = image.getRGB(0, 0, width, height, null, 0, width);
			
			for(int i=0; i < height; i++) {
				for(int j=0; j < width; j++) {
					int pixel = pixels_raw[i * width + j];
					pixels.put((byte)((pixel >> 16) & 0xff));
					pixels.put((byte)((pixel >> 8) & 0xff));
					pixels.put((byte)(pixel & 0xff));
					pixels.put((byte)((pixel >> 24) & 0xff));
				}
			}
			pixels.flip();
			
			id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void bind(int sampler) {
		glActiveTexture(GL_TEXTURE0 + sampler);
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
}