import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectRoundExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        pen.setStroke(Color.RED);
        pen.setLineWidth(5);
        pen.strokeRoundRect(30, 20, 40, 40, 30, 30);
        pen.setFill(Color.BLACK);
        pen.fillRoundRect(0, 100, 80, 10, 5, 5);
        pen.setLineWidth(10);
        pen.fillRoundRect(150, 0, 50, 50, 50, 10);
        pen.strokeRoundRect(150, 0, 50, 50, 50, 10);
    }

    public void draw(GraphicsContext pen) {

    }
}

