package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import shaders.StaticShader;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
//        StaticShader shader = new StaticShader();
//        Renderer renderer = new Renderer(shader);

        float[] vertices = {
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f

        };

        float[] textureCoords = {

                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0


        };

        int[] indices = {
                0,1,3,
                3,1,2,
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22

        };

//        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);

        RawModel model = OBJLoader.loadOBJModel("stall", loader);

        TexturedModel cubeModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));

        ModelTexture texture = cubeModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);

//        Entity entity = new Entity(cubeModel, new Vector3f(0,-1,-10),0,0,0,1);

        Light light = new Light(new Vector3f(0,0,0), new Vector3f(1,1,1));

        Camera camera = new Camera();

        List<Entity> allCubes = new ArrayList<>();
//        Random random = new Random();
//
//        for(int i = 0; i < 200; i++){
//            float x = random.nextFloat() * 100 - 50;
//            float y = random.nextFloat() * 100 - 50;
//            float z = random.nextFloat() * - 300;
////            allCubes.add(new Entity(cubeModel, new Vector3f(0,-1,-10),0,0,0,1);
//            allCubes.add(new Entity(cubeModel, new Vector3f(x,y,z),random.nextFloat() * 180f,random.nextFloat() * 180f,0f,1f));
//        }

        allCubes.add(new Entity(cubeModel, new Vector3f(0,-1,-10),0,0,0,1));

        MasterRenderer renderer = new MasterRenderer();
        while (!Display.isCloseRequested()){
            camera.move();

            for(Entity cube : allCubes){
                renderer.processEntity(cube);
            }
            allCubes.get(0).increaseRotation(0,1,0);
            renderer.render(light, camera);

            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplace();
    }
}
