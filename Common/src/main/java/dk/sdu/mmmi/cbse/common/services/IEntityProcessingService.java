package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface is responsible for processing entities, so they are visible on the screen
 * and update their behavior every game tick.
 */

public interface IEntityProcessingService {

    /**
     *The classes for example PlayerControlSystem, EnemySpaceshipSystem implements the IEntityProcessingService
     * interface, and overrides the process method.
     * Entities are processed within the game world based on the provided game data.
     * Their state and behavior are updated according to game logic
     *
     * OBS! Since both IEntityProcessingService and IPostEntityProcessingService have a
     * method with the same signature (process(GameData gameData, World world)), Java does
     * not inherently distinguish them at runtime. However, the convention in the game framework
     * ensures that all IEntityProcessingService implementations are executed first during
     * a game tick, followed by IPostEntityProcessingService implementations.
     *
     * Preconditions:
     * - gameData and world must not be null
     *
     * Postconditions:
     * - Entities in the world may be updated based on game logic
     * - New entities may be added or existing ones removed as necessary
     *
     *
     * @param gameData The current game state
     * @param world the game world containing all entities
     * @throws
     */
    void process(GameData gameData, World world);
}
