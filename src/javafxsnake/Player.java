package javafxsnake;

import javafx.scene.paint.Color;
import java.util.HashMap;

public class Player extends Sprite{

    public Color playerColour;
    int bodyElements = 20;
    int playerLength = 0;
    
    public Sprite[] bodies = new Sprite[3];
    public double[][] lastPositions = new double[bodyElements][2];
    MovementKeys movementKey = MovementKeys.UP; 

    // Pair movement direction and translation value
    public HashMap<MovementKeys, Double[]> directionValues = new HashMap<>();

    boolean directionFeasible = true;
    MovementKeys[][] movementConflicts = {{MovementKeys.UP, MovementKeys.DOWN}, {MovementKeys.DOWN, MovementKeys.UP}, {MovementKeys.LEFT, MovementKeys.RIGHT}, {MovementKeys.RIGHT, MovementKeys.LEFT}};

    Player(double x, double y, double height, double width, Color playerColour){
        super(x, y, height, width, playerColour);

        this.playerColour = playerColour;

        bodies = initializeBodies(bodyElements);

        // Pair movement direction and translation value
        pairDirectionValues();
    }

    Sprite[] initializeBodies(int bodyElements){
        Sprite[] bodies = new Sprite[bodyElements];

        for(int i = 0; i < bodyElements; i++){
            bodies[i] = new Sprite(280, 240, 40, 40, Color.TRANSPARENT);
        }

        return bodies;
    }

    public void saveLastPositions(){
        for(int i = lastPositions.length - 1; i > -1; i--){
            if(i == 0){
                lastPositions[i][0] = this.getX();
                lastPositions[i][1] = this.getY();
            }else{
                lastPositions[i][0] = lastPositions[i-1][0];
                lastPositions[i][1] = lastPositions[i-1][1];
            }
        }
    }

    public void moveBodies(){
        for(int i = 0; i < this.bodies.length; i++){
            this.bodies[i].setX(this.lastPositions[i][0]);
            this.bodies[i].setY(this.lastPositions[i][1]);
        }
    }

    public void movePlayer(MovementKeys movementKey){

        for(int i = 0; i < movementConflicts.length; i++){
            if(movementKey == movementConflicts[i][0] && this.movementKey == movementConflicts[i][1]){
                directionFeasible = false;
                break;
            }

            directionFeasible = true;
        }

        if(directionFeasible){
            this.movementKey = movementKey;
        }

        this.setX(this.getX() + directionValues.get(this.movementKey)[0]);
        this.setY(this.getY() + directionValues.get(this.movementKey)[1]);
    }

    public void move(MovementKeys movementKey){
        saveLastPositions();
        moveBodies();
        movePlayer(movementKey);
    }

    public void kill(){
        // Reset player length count
        playerLength = 0;
        
        // Reset player position
        this.setX(280); 
        this.setY(280); 

        // Remove player bodies color
        for(int i = 0; i < bodies.length; i++){
            bodies[i].setFill(Color.TRANSPARENT);
        }
    }

    void pairDirectionValues(){
        directionValues.put(MovementKeys.UP, new Double[] {0.0, -40.0});
        directionValues.put(MovementKeys.LEFT, new Double[] {-40.0, 0.0});
        directionValues.put(MovementKeys.DOWN, new Double[] {0.0, 40.0});
        directionValues.put(MovementKeys.RIGHT, new Double[] {40.0, 0.0});
    }

}
