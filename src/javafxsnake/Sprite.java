package javafxsnake;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Sprite extends Rectangle{
    Sprite(double x, double y, double height, double width, Color color){
        super(x, y, height, width);

        // Set colour
        this.setFill(color);
    }
}
