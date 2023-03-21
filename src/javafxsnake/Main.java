package javafxsnake;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.HashMap;


public class Main extends Application {
    // Snake body
    // Rectangle on screen
    // Rectangle mirror player last position
    //  Save player last position
    double[] playerLastPosition = {0,0};

    //  Move rectangle 


    /* Mutliple bodys
     * multiple last positions
     * 
     */

    // TO SOLVE THIS PROBLEM I DID THIS
    // Broke problem down into seperate chunks
    // Solved each problem one by one
    // Solved problem at basic level using shit programming/for one instance
    // Realised needed to replicate basic problem solved multiple times
    // Looked at what is needed in multiples for the basic solution
    // For everything needed in multiples I created an array of objects for them
    // Filled the array with basic objects and not in a fancy way
    // then did FOREACH loop with the singular solution in it for each of the elements in array of objects
    



    // Window parameters
    final int WIDTH = 600, HEIGHT = WIDTH;
    private Pane root = new Pane();
    Scene scene = new Scene(root);

    // Create player
    Rectangle player = new Rectangle(280, 280, 40, 40);

    // Create snake body
    Rectangle body = new Rectangle(280, 240, 40, 40);


    // Bodies
    Rectangle body1 = new Rectangle(280, 240, 40, 40);
    Rectangle body2 = new Rectangle(280, 240, 40, 40);
    Rectangle body3 = new Rectangle(280, 240, 40, 40);
    
    Rectangle[] bodies = {body1, body2, body3};

    // Last positions
    Double[][] lastPositions = {new Double[] {0.0,0.0}, new Double[] {0.0,0.0}, new Double[] {0.0,0.0}}; 

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
        // Save player last position
        playerLastPosition[0] = player.getX();
        playerLastPosition[1] = player.getY();

        // Save last positions
        // For each last position
        System.out.println(lastPositions.length);

        for(int i = lastPositions.length - 1; i > -1; i--){
            // System.out.println(i);
            if(i == 0){
                lastPositions[i][0] = playerLastPosition[0];
                lastPositions[i][1] = playerLastPosition[1];
            }else{
                lastPositions[i][0] = lastPositions[i-1][0];
                lastPositions[i][1] = lastPositions[i-1][1];
            }
        }
        // First position always has to be players
        // Get first position
        // Assign players values
        // Select position
        // Assign position in front

        // Move bodies
        // For each body
        // Set x and y to corresponding increment in last positions array
        for(int i = 0; i < bodies.length; i++){
            bodies[i].setX(lastPositions[i][0]);
            bodies[i].setY(lastPositions[i][1]);
        }


        // Move body
        // body.setX(playerLastPosition[0]);
        // body.setY(playerLastPosition[1]);

        // Move player
        player.setX(player.getX() + directionValues.get(movementKey)[0]);
        player.setY(player.getY() + directionValues.get(movementKey)[1]);

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

        // Draw player
        root.getChildren().add(player);

        // Draw body
        // root.getChildren().add(body);

        // Draw bodies
        root.getChildren().add(body1);
        root.getChildren().add(body2);
        root.getChildren().add(body3);



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