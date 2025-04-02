package dk.sdu.mmmi.cbse.asteriods;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public class AsteroidSplitterImpl implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity e, World world) {
        System.out.println("Splitting Asteroids");
        // Creating new asteroids
        Entity asteroid1 = new Asteroid();
        Entity asteroid2 = new Asteroid();

        // Placing the asteroids apart from each other
        asteroid1.setX(e.getX() + 10);
        asteroid1.setY(e.getY() + 10);
        asteroid2.setX(e.getX() - 10);
        asteroid2.setY(e.getY() - 10);

        float size = e.getRadius() / 2;

        // Making the asteroids smaller
        asteroid1.setRadius(size);
        asteroid2.setRadius(size);

        asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid2.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);

        asteroid1.setRotation(size * 90);
        asteroid2.setRotation(size * 90);

        asteroid1.setCollidedStatus(false);
        asteroid2.setCollidedStatus(false);

        asteroid1.setRemoveEntity(false);
        asteroid2.setRemoveEntity(false);

        // Adding the asteroids into the world
        world.addEntity(asteroid1);
        System.out.println("Spawned asteroid 1 with size: " + size);

        world.addEntity(asteroid2);
        System.out.println("Spawned asteroid 2 with size: " + size);
    }

}
