import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class OvalExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        pen.setStroke(Color.RED);
        pen.setLineWidth(5);
        pen.strokeOval(30, 20, 40, 60);
        pen.setFill(Color.BLACK);
        pen.fillOval(0, 100, 80, 10);
        pen.setLineWidth(10);
        pen.fillOval(150, 0, 50, 50);
        pen.strokeOval(150, 0, 50, 50);
    }

    public void draw(GraphicsContext pen) {

    }
}

