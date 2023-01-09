import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        pen.setStroke(Color.RED);
        pen.strokeLine(0, 0, window.getWidth(), window.getHeight());
        pen.setLineWidth(5);
        pen.strokeLine(30, 20, 40, 40);
        pen.setStroke(Color.BLACK);
        pen.strokeLine(150, 0, 50, 50);
    }

    public void draw(GraphicsContext pen) {

    }
}

