package javafxsnake;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Main extends Application {
    
    // Window parameters
    final int WIDTH = 600, HEIGHT = WIDTH;
    private Pane root = new Pane();
    Scene scene = new Scene(root);

    // Create player
    Rectangle player = new Rectangle(560, 560, 40, 40);

    // Capture input
    void processInput(){
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    System.out.println("W pressed");
                    break;
                case A:
                    System.out.println("A pressed");
                    break;
                case S:
                    System.out.println("S pressed");
                    break;
                case D:
                    System.out.println("D pressed");
                    break;
            } 
        });
    }

    private void update(){
        // Move player
        
        // Down
        player.setY(player.getY() + 40);
        // Up
        player.setY(player.getY() - 40);
        // Left
        player.setX(player.getX() - 40);
        // Right
        player.setX(player.getX() + 40);

    }


    private void initialize(){
        root.setPrefSize(HEIGHT, WIDTH);

        // Draw player
        root.getChildren().add(player);

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now){
                // Cap frames
                if (now - lastUpdate >= 128_000_000) {
                    
                    processInput();
                    update();

                    lastUpdate = now;
                }
            }
        };

        timer.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}