package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerControlSystemTest {
    private GameData gameData;
    private World world;
    private Entity player;
    private PlayerControlSystem playerControlSystem;
    //private GameKeys gameKeys;

    @BeforeEach
    void setUp() {
        player = new Player();
        gameData = new GameData();
        world = new World();
        playerControlSystem = new PlayerControlSystem();
        //gameKeys = new GameKeys();

        player.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        player.setX(100);
        player.setY(100);
        player.setRadius(8);

        world.addEntity(player);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRotateLeft() {
        //Describing initial position
        System.out.println("Initial rotation: " + world.getEntities().iterator().next().getRotation());

        //Processing
        GameKeys.setKeys(GameKeys.LEFT, true);
        playerControlSystem.process(gameData, world);

        //Output and assertion
        System.out.println("Post process rotation: " + world.getEntities().iterator().next().getRotation());
        assertEquals(-5, world.getEntities().iterator().next().getRotation());
    }

    @Test
    void testRotateRight() {
        //Describing initial position
        System.out.println("Initial rotation: " + world.getEntities().iterator().next().getRotation());

        //Processing
        GameKeys.setKeys(GameKeys.LEFT, true);
        playerControlSystem.process(gameData, world);

        //Output and assertion
        System.out.println("Post process rotation: " + world.getEntities().iterator().next().getRotation());
        assertEquals(-5, world.getEntities().iterator().next().getRotation());
    }

    @Test
    void testShooting() {
        Entity bullet = new Bullet();

        // Creating a mock of the BulletSPI, returning the bullet
        BulletSPI mockBulletSPI = mock(BulletSPI.class);
        when(mockBulletSPI.createBullet(player, gameData)).thenReturn(bullet);

        playerControlSystem = new PlayerControlSystem() {
            @Override
            protected Collection<? extends BulletSPI> getBulletSPIs(){
                return List.of(mockBulletSPI);
            }
        };

        //The initial amount of entities
        int initialEntityCount = world.getEntities().size();

        // Processing
        GameKeys.setKeys(GameKeys.SPACE, true);
        playerControlSystem.process(gameData, world);

        //Asserting amount of entities
        int newEntityCount = world.getEntities().size();
        assertEquals(initialEntityCount + 1, newEntityCount);
        assertTrue(world.getEntities().contains(bullet));
    }

    @Test
    void testMoveUp() {
        //Describing initial position
        System.out.println("Initial X: " + player.getX());
        System.out.println("Initial Y: " + player.getY());

        //Processing
        GameKeys.setKeys(GameKeys.UP, true);
        playerControlSystem.process(gameData, world);

        //Output and assertion
        System.out.println("Post process X: " + player.getX());
        System.out.println("Post process Y: " + player.getY());
        assertEquals(101, player.getX());
        assertEquals(100, player.getY());
    }


}

