
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemyspaceshipsystem.EnemySpaceShipPlugin;
import dk.sdu.mmmi.cbse.enemyspaceshipsystem.EnemySpaceShipControlSystem;

module EnemySpaceship {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with EnemySpaceShipPlugin;
    provides IEntityProcessingService with EnemySpaceShipControlSystem;

}
