package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for post-processing entities after their main update logic has been executed
 */
public interface IPostEntityProcessingService {

    /**
     * Applies post-processing to entities after the primary game logic has been executed.
     *
     * OBS! Since both IEntityProcessingService and IPostEntityProcessingService have a
     * method with the same signature (process(GameData gameData, World world)), Java does
     * not inherently distinguish them at runtime. However, the convention in the game framework
     * ensures that all IEntityProcessingService implementations are executed first during
     * a game tick, followed by IPostEntityProcessingService implementations.
     *
     * Preconditions:
     * - gameData and world is not null
     *
     * Postconditions:
     * - Entities in the world may have final adjustments applied
     * - Collisions or cleanup operations may be performed
     *
     * @param gameData The current game state
     * @param world The game world containing all entities
     */
    void process(GameData gameData, World world);
}
