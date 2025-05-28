package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private static ModuleLayer moduleLayer;

    public static void main(String[] args) {
        // Creating the layers
        moduleLayer = createLayer("Plugins");
        launch(Main.class);
    }

    private static ModuleLayer createLayer(String from) {
        //The ModuleFinder will locate the modules/all .jar files
        ModuleFinder finder = ModuleFinder.of(Paths.get(from));
        // Retrieves the module names from the JARs found
        List<String> plugins = finder.findAll().stream().map(ModuleReference::descriptor).map(ModuleDescriptor::name).collect(Collectors.toList());
        // Get the current boot layer
        ModuleLayer parent = ModuleLayer.boot();
        // Resolve split packages on the given modules
        Configuration configuration = parent.configuration().resolve(finder, ModuleFinder.of(), plugins);
        // Create and return a new ModuleLayer
        // The classloader contains the found plugins
        return parent.defineModulesWithOneLoader(configuration, ClassLoader.getSystemClassLoader());
    }

        @Override
    public void start(Stage window) throws Exception {
        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(text);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKeys(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKeys(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKeys(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKeys(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKeys(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKeys(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKeys(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKeys(GameKeys.SPACE, false);
            }

        });

            // Lookup all Game Plugins using ServiceLoader
            for (IGamePluginService iGamePlugin : getPluginServices()) {
                iGamePlugin.start(gameData, world);
            }
            for (Entity entity : world.getEntities()) {
                Polygon polygon = new Polygon(entity.getPolygonCoorinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
        render();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    private void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoorinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }

    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(moduleLayer, IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(moduleLayer, IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(moduleLayer, IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
