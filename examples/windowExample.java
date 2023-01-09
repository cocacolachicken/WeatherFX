import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class windowExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // This example allows for a resized window that can be resized
    // The title is changed to 'My Example'
    // The background colour fades as it is resized.
    int value = 0;
    public void setup(GraphicsContext pen) {
        window.setTitle("My Example");
        window.setWidth(200);
        window.setHeight(80);
        window.setResizable(true);
    }

    public void draw(GraphicsContext pen) {
        window.setBackground(Color.GREEN);
        pen.setFill(Color.rgb(value, value, value));
        pen.fillRect(25, 25, 50, 50);
    }

    public void windowChanged() {
        value = value + 5;
        if (value > 255) {
            value = 0;
        }
    }
}


