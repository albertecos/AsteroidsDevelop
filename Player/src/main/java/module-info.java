import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Player {
    requires Common;
    requires CommonBullet;
    uses BulletSPI;
    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
}