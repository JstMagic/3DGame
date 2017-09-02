package engineTester;

import entities.Entity;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
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
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        float[] vertices = {
                // Triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f,0.5f,0
        };

        float[] textureCoords = {
                0,0, //V0
                0,1,
                1,1,
                1,0
        };

        int[] indices = {
                0,1,3, // top left triangle V0, V1, V3
                3,1,2 // bottom left triangle V3,V1,V2
        };

        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);

        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("brick")));

        Entity entity = new Entity(staticModel, new Vector3f(0,0,-1),0,0,0,1);

        while (!Display.isCloseRequested()){
            entity.increasePosition(0,0,-0.1f);
//            entity.increaseRotation(0,1,0);
            renderer.prepare();
            shader.start();
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplace();
    }
}
