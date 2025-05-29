package dk.sdu.mmmi.cbse.asteriods;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    @Override
    public void process(GameData gameData, World world) {
        for(Entity asteroid : world.getEntity(Asteroid.class)){
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            }else if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(0);
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(asteroid.getX() - gameData.getDisplayWidth());
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            }else if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(0);
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(asteroid.getY() - gameData.getDisplayHeight());
            }
            asteroidCollision(asteroid, world);
        }
    }
    public void asteroidCollision(Entity asteroid, World world) {
        boolean collided = asteroid.getCollidedStatus();

        // Checking whether the asteroid has collided with another entity
        if (collided){
            // Splitting the asteroids
            asteroidSplitter.createSplitAsteroid(asteroid, world);
        }
    }
}
