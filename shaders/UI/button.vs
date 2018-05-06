#version 330

attribute vec3 vertices;
uniform mat4 projection;

attribute vec2 textures;

varying vec2 tex_coords;

void main() {
	tex_coords = textures;
	gl_Position = projection * vec4(vertices, 1);
}