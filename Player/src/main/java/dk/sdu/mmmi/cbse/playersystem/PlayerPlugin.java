package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = new Player();
        player.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        player.setX(gameData.getDisplayHeight() / 2);
        player.setY(gameData.getDisplayWidth() / 2);
        player.setRadius(8);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity e : world.getEntities()) {
            if(e.getClass() == Player.class) {
                world.removeEntity(e);
            }
        }
    }
}
