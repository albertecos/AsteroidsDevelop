package dk.sdu.mmmi.cbse.asteriods;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public class AsteroidSplitterImpl{
    public void createSplitAsteroid(Entity e, World world) {
        if(e.getRadius() <= 5) {
            for (Entity entity : world.getEntities()) {
                if (entity.getClass().getSimpleName().equals("Player")) {
                    entity.setDestroyedAsteroids(entity.getDestroyedAsteroids() + 1);
                }
            }
            e.setDestroyedAsteroids(e.getDestroyedAsteroids() + 1);
            world.removeEntity(e);
            return;
        }

        world.removeEntity(e);
        // Creating new asteroids
        Entity asteroid1 = new Asteroid();
        Entity asteroid2 = new Asteroid();

        // Placing the asteroids apart from each other
        asteroid1.setX(e.getX() + 20);
        asteroid1.setY(e.getY() + 20);
        asteroid2.setX(e.getX() - 50);
        asteroid2.setY(e.getY() - 50);

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

        // Adding the asteroids into the world
        world.addEntity(asteroid1);

        world.addEntity(asteroid2);
    }

}
