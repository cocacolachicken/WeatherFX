import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class keyCodeExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // Click on the window to give it focus,
    // and press the 'B' key.
    int fillVal = 0;
    public void setup(GraphicsContext pen) {
        pen.setFill(Color.WHITE);
    }

    public void draw(GraphicsContext pen) {
        pen.setFill(Color.rgb(fillVal, fillVal, fillVal));
        pen.fillRect(25, 25, 50, 50);
    }

    public void keyPressed() {
        // Uses keyCodes to change the colour using a 'global' variable
        if (key.keyCode == KeyCode.UP) {
            fillVal = 255;
        } else if (key.keyCode == KeyCode.DOWN) {
            fillVal = 0;
        } else {
            fillVal = 126;
        }
    }
}

