package shaders;

/**
 * Created by root on 9/2/17.
 */
public class StaticShader extends ShaderProgram {

    public StaticShader(){
        super("vertexShader.vs", "fragmentShader.fs");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "pass_textureCoords");
    }
}
