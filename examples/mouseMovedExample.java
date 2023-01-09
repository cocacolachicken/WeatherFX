import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class mouseMovedExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // Move the mouse across the image
    // to change its value
    int value = 0;
    public void setup(GraphicsContext pen) {}

    public void draw(GraphicsContext pen) {
        pen.setFill(Color.rgb(value, value, value));
        pen.fillRect(25, 25, 50, 50);
    }

    public void mouseMoved() {
        value = value + 5;
        if (value > 255) {
            value = 0;
        }
    }
}


