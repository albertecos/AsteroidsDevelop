package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        //Two loops for all entities in the world
        for(Entity entity1 : world.getEntities()) {
            for(Entity entity2 : world.getEntities()) {

                //If the two entities are identical, skip the iteration
                if(entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                //CollisionDetector
                if(this.collides(entity1, entity2)) {
                    world.removeEntity(entity1);
                    world.removeEntity(entity2);
                }
            }
        }
    }

    public Boolean collides(Entity e1, Entity e2) {
        float dx = (float) e1.getX() - (float) e2.getX();
        float dy = (float) e1.getY() - (float) e2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (e1.getRadius() + e2.getRadius());
    }


}
