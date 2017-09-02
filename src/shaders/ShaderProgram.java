package shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by root on 9/2/17.
 */
public abstract class ShaderProgram {
    private int programID;
    private int vertextShaderID;
    private int fragmentShaderID;

    public ShaderProgram(String vertexFile, String fragmentFile){
        vertextShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);

        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertextShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
    }

    public void  start(){
      GL20.glUseProgram(programID);
    }

    public void stop(){
        GL20.glUseProgram(0);
    }

    public void cleanUp(){
        stop();
        GL20.glDetachShader(programID, vertextShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertextShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    private static int loadShader(String file, int type){
        StringBuffer shaderSource = new StringBuffer();

        try {
            BufferedReader reader = null;
            if(type == GL20.GL_VERTEX_SHADER){
                reader = new BufferedReader(new FileReader("src/shaders/"+file));
            }else if(type == GL20.GL_FRAGMENT_SHADER){
                reader = new BufferedReader(new FileReader("src/shaders/"+file));
            }

            String line;
            while ((line = reader.readLine())!=null){
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Could not read file!");
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderID;

    }
}
