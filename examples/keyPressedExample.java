import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class keyPressedExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // Click on the image to give it focus,
    // and then press any key.
    int value = 0;
    public void setup(GraphicsContext pen) {}

    public void draw(GraphicsContext pen) {
        pen.setFill(Color.rgb(0, 0, value));
        pen.fillRect(25, 25, 50, 50);
    }

    public void keyPressed() {
        if (value == 0) {
            value = 255;
        } else {
            value = 0;
        }
    }
}


