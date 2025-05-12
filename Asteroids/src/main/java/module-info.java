import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid{
    requires Common;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteriods.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteriods.AsteroidProcessor;
}