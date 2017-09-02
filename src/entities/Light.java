package entities;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by root on 9/2/17.
 */
public class Light {

    private Vector3f postion;
    private Vector3f colour;

    public Light(Vector3f postion, Vector3f colour) {
        this.postion = postion;
        this.colour = colour;
    }

    public Vector3f getPostion() {
        return postion;
    }

    public void setPostion(Vector3f postion) {
        this.postion = postion;
    }

    public Vector3f getColour() {
        return colour;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }
}
