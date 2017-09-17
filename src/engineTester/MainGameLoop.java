package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

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

        //***********TERRAIN TEXTURE STUFF***********

        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grass"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

        //*******************************************

        ModelData data = OBJFileLoader.loadOBJ("tree");
        RawModel treeModel = loader.loadToVAO(data.getVertices(), data.getTextureCoords(),
                data.getNormals(), data.getIndices());

        TexturedModel staticModel = new TexturedModel(treeModel,
                new ModelTexture(loader.loadTexture("tree")));

        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("grassTexture")));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLighting(true);

        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),
                new ModelTexture(loader.loadTexture("fern")));
        fern.getTexture().setHasTransparency(true);
        fern.getTexture().setUseFakeLighting(true);

        List<Entity> entities = new ArrayList<>();


        Random random = new Random();
        for(int i=0;i<500;i++){
            entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 -400,0,
                    random.nextFloat() *-600),0,0,0,3));

            entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 - 400, 0,
                    random.nextFloat() * -600), 0,0,0,1));

            entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 - 400, 0,
                    random.nextFloat() * -600), 0,0,0,0.6f));
        }

        Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));


        Terrain terrain = new Terrain(0,-1,loader, texturePack, blendMap);
        Terrain terrain2 = new Terrain(-1,-1,loader, texturePack, blendMap);

        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer();

        while (!Display.isCloseRequested()){
            camera.move();

            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplace();
    }
}
