package dk.sdu.mmmi.cbse.enemyspaceshipsystem;

import com.sun.source.util.Plugin;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemySpaceshipPlugin implements IGamePluginService {

    private final Random random = new Random();

    private Entity enemySpaceship;

    public EnemySpaceshipPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // enemySpaceship-entety to the world

        enemySpaceship = createEnemySpaceship(gameData);
        world.addEntity(enemySpaceship);
    }

    private Entity createEnemySpaceship(GameData gameData) {
        enemySpaceship = new EnemySpaceship();

        float x = random.nextFloat(gameData.getDisplayWidth());
        float y = random.nextFloat(gameData.getDisplayHeight());
        enemySpaceship.setX(x);
        enemySpaceship.setY(y);

        enemySpaceship.setRadius(8);
        enemySpaceship.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);

        return enemySpaceship;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemySpaceship);
    }
}
