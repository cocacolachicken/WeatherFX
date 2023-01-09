import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class mouseClickedExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // Click anywhere on the image to change the fill of
    // the rectangle
    int value = 0;
    public void setup(GraphicsContext pen) {}

    public void draw(GraphicsContext pen) {
        pen.setFill(Color.rgb(0, 0, value));
        pen.fillRect(25, 25, 50, 50);
    }

    public void mouseClicked() {
        if (value == 0) {
            value = 255;
        } else {
            value = 0;
        }
    }
}


