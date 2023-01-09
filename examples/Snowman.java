import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Snowman {
    private double offset;

    public Snowman(double xPos) {
        offset = xPos;
    }

    public void draw(GraphicsContext pen) {
        pen.setFill(Color.WHITE);
        pen.fillOval(offset, 200, 200, 200);
        pen.fillOval(offset+25, 100, 150, 150);
        pen.fillOval(offset+50, 25, 100, 100);

        pen.setFill(Color.BLACK);
        pen.fillOval(offset+80,50, 10, 10);
        pen.fillOval(offset+110,50, 10, 10);

        pen.setStroke(Color.BLACK);
        pen.strokeArc(offset+80,  70, 40, 20, 225, 90, ArcType.OPEN);
    }
}
