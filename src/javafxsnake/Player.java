package javafxsnake;

import javafx.scene.shape.Rectangle;

public class Player extends Rectangle{

    int bodyElements = 3;
    
    public Rectangle[] bodies = new Rectangle[3];
    public double[][] lastPositions = new double[bodyElements][2];

    Player(double x, double y, double height, double width){
        super(x, y, height, width);

        bodies = initializeBodies(bodyElements);
    }

    Rectangle[] initializeBodies(int bodyElements){
        Rectangle[] bodies = new Rectangle[bodyElements];

        for(int i = 0; i < bodyElements; i++){
            bodies[i] = new Rectangle(280, 240, 40, 40);
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
