package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface representing a game plugin service, responsible for initializing and terminating game components
 * FX the component Bullet has a class BulletPlugin that implements IGamePluginService. This class would
 * override the start() method to initialize bullets and add them to the game world, and the stop() method
 * to remove them when the game ends or the plugin is unloaded.
 */
public interface IGamePluginService {
    /**
     * Initializes the game plugin be adding necessary entities to the game world
     *
     * Preconditions:
     * - gameData and world is not null
     *
     * Postconditions:
     * - Relevant entities are added to the world to start the plugin
     *
     * @param gameData The current game state
     * @param world The game world containing all entities
     */
    void start(GameData gameData, World world);

    /**
     * Terminated the game plugin by removing its associated entities from the game world
     *
     * Preconditions:
     * - gameData and world is not null
     * - The plugin must have previously added entities to the world
     *
     * Postconditions:
     * - Entities related to this plugin are removed from the world
     *
     * @param gameData The current game state
     * @param world The game world containing all entities
     */
    void stop(GameData gameData, World world);
}
