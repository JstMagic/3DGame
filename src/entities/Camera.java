package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by root on 9/2/17.
 */
public class Camera {
    private Vector3f position = new Vector3f(0, 5, 0);
    private float pitch = 10;
    private float yaw ;
    private float roll;

    private float speead = 3;

    public Camera() {

    }

    public void move() {

        if(Mouse.isButtonDown(0)){
            yaw =  - (Display.getWidth() + Mouse.getX() / 2);
            pitch =  (Display.getHeight() / 2) - Mouse.getY();

        }else {
            yaw = 0;
//            pitch = 10;
        }

        if (pitch >= 90)
        {

            pitch = 90;

        }
        else if (pitch <= -90)
        {

            pitch = -90;

        }

        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= (float)Math.cos(Math.toRadians(yaw)) *speead;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += (float)Math.cos(Math.toRadians(yaw)) *speead;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x +=  (float)Math.cos(Math.toRadians(yaw)) *speead;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x -= (float)Math.cos(Math.toRadians(yaw)) *speead;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            position.y+= speead;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            position.y-= speead;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
