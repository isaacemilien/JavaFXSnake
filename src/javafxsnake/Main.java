package javafxsnake;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;

public class Main extends Application {
    
    // Window parameters
    final int WIDTH = 600, HEIGHT = WIDTH;
    private Pane root = new Pane();
    
    private Parent initialize(){
        root.setPrefSize(HEIGHT, WIDTH);

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now){
                // Cap frames
                if (now - lastUpdate >= 128_000_000) {
                    lastUpdate = now;
                }
            }
        };

        timer.start();

        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(initialize()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}