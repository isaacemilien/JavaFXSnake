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

    // Snake growing
    // Run into self
    // Death

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
    Player player = new Player(280, 280, 40, 40);

    // Last movement key pressed
    MovementKeys movementKey = MovementKeys.DOWN;

    // Pair movement direction and translation value
    HashMap<MovementKeys, Double[]> directionValues = new HashMap<>();

    void pairDirectionValues(){
        directionValues.put(movementKey.UP, new Double[] {0.0, -40.0});
        directionValues.put(movementKey.LEFT, new Double[] {-40.0, 0.0});
        directionValues.put(movementKey.DOWN, new Double[] {0.0, 40.0});
        directionValues.put(movementKey.RIGHT, new Double[] {40.0, 0.0});
    }

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

        // Detect player head on food
        if(player.getX() == food.getX() && player.getY() == food.getY()){

            System.out.println("Player head on food");

            // Give food random position
            food.setX(generateRandomPosition());
            food.setY(generateRandomPosition());
        }

        // Move player
        player.move(directionValues.get(movementKey)[0], directionValues.get(movementKey)[1]);

        // Detect when player out of bounds
        if(player.getX() < 0 || player.getX() > WIDTH || player.getY() < 0 || player.getY() > HEIGHT){
            System.out.println("Out of bounds");

            // Reset player position
            player.setX(280); 
            player.setY(280); 
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

        // Initialize direction value pairs
        pairDirectionValues();

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