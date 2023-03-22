package javafxsnake;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Player extends Sprite{

    public Color playerColour;
    int bodyElements = 10;
    int playerLength = 0;
    
    public Sprite[] bodies = new Sprite[3];
    public double[][] lastPositions = new double[bodyElements][2];

    Player(double x, double y, double height, double width, Color playerColour){
        super(x, y, height, width, playerColour);

        this.playerColour = playerColour;

        bodies = initializeBodies(bodyElements);
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

    public void movePlayer(Double x, Double y){
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    public void move(Double x, Double y){
        saveLastPositions();
        moveBodies();
        movePlayer(x, y);
    }

}
