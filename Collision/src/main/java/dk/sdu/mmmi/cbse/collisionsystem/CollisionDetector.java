package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world){

        //Two loops for all entities in the world
        for(Entity entity1 : world.getEntities()) {
            for(Entity entity2 : world.getEntities()) {

                //If the two entities are identical, skip the iteration
                if(entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {
                    System.out.println("collides " + entity1.getID() + " with " + entity2.getID());
                    entity1.setCollidedStatus(true);
                    entity2.setCollidedStatus(true);
                    System.out.println(entity1.getCollidedStatus() + " " + entity2.getCollidedStatus());
                }
            }

        }
    }
    //This method is based in vector distances, maybe check up on that for
    //further explanation and formulas
    public Boolean collides(Entity e1, Entity e2) {
        float dx = (float) e1.getX() - (float) e2.getX(); //subtraction gives the horizontal distance (dx) between the two entities
        float dy = (float) e1.getY() - (float) e2.getY(); //subtraction gives the vertical distance (dy) between the two entities

        // Pythagoras is used to calculate the distances
        // dx * dx = dx^2   and   dy * dy = dy^2
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        // if distance is smaller than the sum of the two entities the method
        // will return true, because then ofc they will collide
        return distance < (e1.getRadius() + e2.getRadius());
    }
}
