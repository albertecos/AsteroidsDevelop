import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module AsteroidsDouble {
    requires Common;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;


}