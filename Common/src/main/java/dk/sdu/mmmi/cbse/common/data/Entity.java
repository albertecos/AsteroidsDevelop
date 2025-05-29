package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private boolean collidedStatus;
    private int health;
    private int destroyedAsteroids;

    public String getID(){return ID.toString();}
    public double[] getPolygonCoordinates(){return polygonCoordinates;}

    public void setPolygonCoordinates(double... coorinates) {
        this.polygonCoordinates = coorinates;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setCollidedStatus(boolean collidedStatus) {
        this.collidedStatus = collidedStatus;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDestroyedAsteroids(int destroyedAsteroids) {
        this.destroyedAsteroids = destroyedAsteroids;
    }

    public boolean getCollidedStatus() {
        return collidedStatus;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public float getRadius() {
        return radius;
    }

    public int getHealth() {
        return health;
    }

    public int getDestroyedAsteroids() {
        return destroyedAsteroids;
    }
}
