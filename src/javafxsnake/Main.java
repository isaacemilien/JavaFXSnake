package javafxsnake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Random;

import javax.xml.transform.Templates;


public class Main extends Application {

    // Window parameters
    final int WIDTH = 600, HEIGHT = WIDTH;
    private Pane root = new Pane();
    Scene scene = new Scene(root);

    // Create food
    Sprite food = new Sprite(560, 560, 40, 40, Color.RED);

    // Generate random food position
    Random random = new Random();

    double generateRandomPosition(){
        return (random.nextInt((14 - 1) + 1) + 1) * 40;
    }

    // Create player
    Player player = new Player(280, 280, 40, 40, Color.BLUE);

    // Last movement key pressed
    MovementKeys movementKey = MovementKeys.DOWN;

    // Capture input
    void processInput(){
        scene.setOnKeyPressed(e -> {
            // Save what movement key last pressed
            switch (e.getCode()) {
                // Up
                case W:
                    System.out.println("W pressed");
                    movementKey = MovementKeys.UP;
                    break;

                // Left
                case A:
                    System.out.println("A pressed");
                    movementKey = MovementKeys.LEFT;
                    break;

                // Down
                case S:
                    System.out.println("S pressed");
                    movementKey = MovementKeys.DOWN;
                    break;

                // Right
                case D:
                    System.out.println("D pressed");
                    movementKey = MovementKeys.RIGHT;
                    break;
            } 
        });
    }

    private void update(){
        // Cycle through all player bodies
        for(int i = 0; i < player.bodies.length; i++){

            // Check if player head and body collide
            if(player.getX() == player.bodies[i].getX() && player.getY() == player.bodies[i].getY() && player.bodies[i].getFill() == player.playerColour){

                // Kill player
                player.kill();
            }
        }

        // Detect player head on food
        if(player.getX() == food.getX() && player.getY() == food.getY()){

            System.out.println("Player head on food");

            // Give food random position
            food.setX(generateRandomPosition());
            food.setY(generateRandomPosition());

            // Make first body piece visible
            player.bodies[player.playerLength].setFill(player.playerColour);
            player.playerLength++;
        }

        // Move player
        player.move(movementKey);

        // Detect when player out of bounds
        if(player.getX() < 0 || player.getX() > WIDTH || player.getY() < 0 || player.getY() > HEIGHT){
            System.out.println("Out of bounds");

            // Kill player
            player.kill();
        }
    }

    private void initialize(){
        // Set window size
        root.setPrefSize(HEIGHT, WIDTH);

        // Set food position
        food.setX(generateRandomPosition());
        food.setY(generateRandomPosition());

        // Draw food
        root.getChildren().add(food);
        
        // Draw player
        root.getChildren().add(player);

        // Draw body
        root.getChildren().addAll(player.bodies);

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

        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}