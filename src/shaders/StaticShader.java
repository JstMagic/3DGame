package shaders;

import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by root on 9/2/17.
 */
public class StaticShader extends ShaderProgram {

    private int location_transformationMatrix;
    private int location_projectionMatrix;

    public StaticShader(){
        super("vertexShader.vs", "fragmentShader.fs");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "pass_textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix4f){
        super.loadMatrix(location_transformationMatrix, matrix4f);

    }

    public  void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }
}
