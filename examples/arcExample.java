import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.lang.Math;

public class arcExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        window.setBackground(Color.GREY);
        pen.setFill(Color.YELLOW);
        pen.fillRect(50, 50, 50, 50);
        pen.setFill(Color.BLACK);
        pen.setStroke(Color.PINK);
        pen.fillArc(50, 50, 50, 50, 0, 25*Math.PI, ArcType.OPEN);
        pen.fillArc(50, 55, 60, 60, 90, 30*Math.PI, ArcType.ROUND);
        pen.strokeArc(50, 55, 70, 70, 270, 35*Math.PI, ArcType.CHORD);
        pen.strokeArc(50, 55, 80, 80, 0, 35*Math.PI, ArcType.OPEN);
    }

    public void draw(GraphicsContext pen) {

    }
}
