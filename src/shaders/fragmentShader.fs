#version 330

in vec2 pass_textureCoords;

out vec4 out_colour;

uniform sampler2D textureSampler;
uniform vec3 lightColour;

void main(void){

  out_colour = texture(textureSampler, pass_textureCoords);
}