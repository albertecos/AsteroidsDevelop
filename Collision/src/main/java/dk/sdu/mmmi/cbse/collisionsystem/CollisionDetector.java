package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        //Two loops for all entities in the world
        for(Entity entity1 : world.getEntities()) {
            for(Entity entity2 : world.getEntities()) {

                //If the two entities are identical, skip the iteration
                if(entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                //When bullets are fired upon Asteroids, they should split into two smaller
                //Asteroids and when small enough they should be destroyed.
                if(entity1 instanceof Asteroid && entity2 instanceof Bullet) {
                    System.out.println("Calling Splitting Asteroids 1");
                    world.removeEntity(entity2);
                    asteroidCollision(entity1, world);
                    continue;
                }else if (entity2 instanceof Asteroid && entity1 instanceof Bullet) {
                    System.out.println("Calling Splitting Asteroids 2");
                    world.removeEntity(entity1);
                    asteroidCollision(entity2, world);
                    continue;
                }

                //CollisionDetector
                if(this.collides(entity1, entity2)) {
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
    }

    /*
    This method is based in vector distances, maybe check up on that for
    further explanation and formulas
     */
    public Boolean collides(Entity e1, Entity e2) {
        float dx = (float) e1.getX() - (float) e2.getX(); //subtraction gives the horizontal distance (dx) between the two entities
        float dy = (float) e1.getY() - (float) e2.getY(); //subtraction gives the vertical distance (dy) between the two entities

        /*
        Pythagoras is used to calculate the distances
        dx * dx = dx^2   and   dy * dy = dy^2
         */
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        /*
        if distance is smaller than the sum of the two entities the method
        will return true, because then ofc they will collide
         */
        return distance < (e1.getRadius() + e2.getRadius());
    }
    //This method enables the asteroid-splitter functionality
    public void asteroidCollision(Entity asteroid, World world) {
        Collection<? extends IAsteroidSplitter> asteroidSplitter = getAsteroidSplitter();
        if (asteroidSplitter.isEmpty()) {
            return;
        }
        for(IAsteroidSplitter splitter : asteroidSplitter) {
            splitter.createSplitAsteroid(asteroid, world);
        }
    }

    /**
     * Loads asteroid splitting service.
     *
     * @return Collection of asteroid splitters
     */
    private Collection<? extends IAsteroidSplitter> getAsteroidSplitter() {
        return ServiceLoader.load(IAsteroidSplitter.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
