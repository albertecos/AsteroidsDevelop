package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoorinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private boolean collidedStatus;
    private boolean removeEntity;

    public String getID(){return ID.toString();}
    public double[] getPolygonCoordinates(){return polygonCoorinates;}

    public void setPolygonCoordinates(double... coorinates) {
        this.polygonCoorinates = coorinates;
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

    public void setRemoveEntity(boolean removeEntity) {
        this.removeEntity = removeEntity;
    }

    public boolean getRemoveEntity() {
        return removeEntity;
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
}
