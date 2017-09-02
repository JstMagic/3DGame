package engineTester;

import models.TexturedModel;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

/**
 * Created by root on 8/31/17.
 */
public class MainGameLoop {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        DisplayManager.createDisplay(); //open display

        /**
         * game rendering
         * main game functions
         * game logic
         */
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();


        float[] vertices = {
                // Triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f,0.5f,0
        };

        int[] indices = {
                0,1,3, // top left triangle V0, V1, V3
                3,1,2 // bottom left triangle V3,V1,V2
        };

        float[] textureCoords = {
                0,0, //V0
                0,1,
                1,1,
                1,0
        };

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("brick"));
        TexturedModel texturedModel = new TexturedModel(model,texture);

        while (!Display.isCloseRequested()){
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplace();
    }
}
