import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        pen.setStroke(Color.RED);
        pen.setLineWidth(5);
        pen.strokeRect(30, 20, 40, 40);
        pen.setFill(Color.BLACK);
        pen.fillRect(0, 100, 80, 10);
        pen.setLineWidth(10);
        pen.fillRect(150, 0, 50, 50);
        pen.strokeRect(150, 0, 50, 50);
    }

    public void draw(GraphicsContext pen) {

    }
}
