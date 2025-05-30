package dk.sdu.mmmi.cbse.enemyspaceshipsystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemySpaceShipControlSystem implements IEntityProcessingService {

    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        // Loop through all entities in the world that are enemies
        for (Entity enemySpaceship : world.getEntity(EnemySpaceShip.class)) {

            if (random.nextDouble() < 0.05) { // 5% chance per frame to change direction
                enemySpaceship.setRotation(random.nextInt(360)); // Random rotation between 0 and 359 degrees
            }
            // Move the enemy forward based on its current rotation
            double changeX = Math.cos(Math.toRadians(enemySpaceship.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemySpaceship.getRotation()));
            enemySpaceship.setX(enemySpaceship.getX() + changeX);
            enemySpaceship.setY(enemySpaceship.getY() + changeY);

            // Keep the enemy within screen boundaries
            if (enemySpaceship.getX() < 0) {
                enemySpaceship.setX(1);
            }
            if (enemySpaceship.getX() > gameData.getDisplayWidth()) {
                enemySpaceship.setX(gameData.getDisplayWidth() - 1);
            }
            if (enemySpaceship.getY() < 0) {
                enemySpaceship.setY(1);
            }
            if (enemySpaceship.getY() > gameData.getDisplayHeight()) {
                enemySpaceship.setY(gameData.getDisplayHeight() - 1);
            }
            // 3% chance per frame to shoot
            if (random.nextDouble() < 0.03) {
                shootBullet(enemySpaceship, gameData, world);
            }
            handleCollision(enemySpaceship, world);
        }
    }
    private void shootBullet(Entity enemySpaceship, GameData gameData, World world) {
        for (BulletSPI bulletSPI : getBulletSPIs()) {
            Entity bullet = bulletSPI.createBullet(enemySpaceship, gameData);
            if (bullet != null) {
                world.addEntity(bullet);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    private void handleCollision(Entity enemy, World world) {
        if (enemy.getCollidedStatus() && enemy.getHealth() == 0) {
            world.removeEntity(enemy);
        }else if (enemy.getCollidedStatus() && enemy.getHealth() > 10){
            enemy.setHealth(enemy.getHealth() - 10);
            enemy.setCollidedStatus(false);
        }
    }
}
