import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class keyExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // Click on the window to give it focus,
    // and press the 'B' key.
    public void setup(GraphicsContext pen) {
        pen.setFill(Color.WHITE);
    }

    public void draw(GraphicsContext pen) {
        if (key.pressed) {
            // Sets the fill to red
            if (key.key == 'b' || key.key == 'B') {
                pen.setFill(Color.RED);
            }
        } else {
            // Otherwise set the fill to the current fill Value (Black 0, White 255, Gray 126);
            pen.setFill(Color.WHITE);
        }

        pen.fillRect(25, 25, 50, 50);
    }
}