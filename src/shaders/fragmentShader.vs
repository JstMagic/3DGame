#version 330

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 out_colour;

uniform sampler2D modelTexure;
uniform vec3 lightColour;
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColour;

void main(void){
  vec3 unitNormal = normalize(surfaceNormal);
  vec3 unitLightVector = normalize(toLightVector);

  float nDot1 = dot(unitNormal, unitLightVector);
  float brightness = max(nDot1,0.2);
  vec3 diffuse = brightness * lightColour;

  vec3 unitVectorToCamera = normalize(toCameraVector);
  vec3 lightDirection = -unitLightVector;
  vec3 reflectedLightDirection = reflect(lightDirection , unitNormal);

  float specularFactor = dot(reflectedLightDirection , unitVectorToCamera);
  specularFactor = max(specularFactor,0.0);
  float dampedFactor = pow(specularFactor, shineDamper);
  vec3 finalSpecular = dampedFactor * reflectivity * lightColour;

  vec4 textureColour = texture(modelTexure,pass_textureCoords);
  if(textureColour.a < 0.5){
    discard;
  }
  out_colour = vec4(diffuse,1.0) * textureColour + vec4(finalSpecular, 1.0);
  out_colour = mix(vec4(skyColour,1.0), out_colour, visibility);

}