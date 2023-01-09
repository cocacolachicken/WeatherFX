import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PointExample extends ProcessingFX {

    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {

    }

    public void draw(GraphicsContext pen) {
        Point p = new Point();
        Point follower = new Point(mouse.getX(), mouse.getY());
        p.draw(pen);
        follower.draw(pen);
    }
}
