package javafxsnake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle{
    Sprite(double x, double y, double height, double width, Color color){
        super(x, y, height, width);

        // Set colour
        this.setFill(color);
    }
}
