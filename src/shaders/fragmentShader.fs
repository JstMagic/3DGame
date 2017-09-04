#version 330

in vec2 pass_textureCoords;
out vec4 out_colour;
in vec3 surfaceNormal;
in vec3 toLightVector;

uniform sampler2D textureSampler;
uniform vec3 lightColour;

void main(void){
  vec3 unitNormal = normalize(surfaceNormal);
  vec3 unitLightVector = normalize(toLightVector);

  float nDot1 = dot(unitNormal, unitLightVector);
  float brightness = max(nDot1,0.0);
  vec3 diffuse = brightness * lightColour;

  out_colour = vec4(diffuse,1.0) * texture(textureSampler, pass_textureCoords);
}