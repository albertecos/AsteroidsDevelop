package dk.sdu.mmmi.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    private ApplicationContext context;

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void init() {
        // Only plain Spring context (NO Spring Boot)
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    @Override
    public void start(Stage window) {
        // Print all Spring beans
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        // Get your Game bean and start it
        Game game = context.getBean(Game.class);
        try {
            game.start(window);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        game.render();
    }
}
