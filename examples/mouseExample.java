import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class mouseExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // Mouse the mouse around rapidly
    public void setup(GraphicsContext pen) {
        pen.setLineWidth(5);
        pen.setStroke(Color.BLUE);
    }

    public void draw(GraphicsContext pen) {
        pen.clearRect(0, 0, window.getWidth(), window.getHeight());
        pen.strokeLine(mouse.x, mouse.y, mouse.px, mouse.py);
        System.out.println("(" + mouse.x + ", " + mouse.y + ") " +
                "<- (" + mouse.px + ", " + mouse.py + ")");
    }
}



