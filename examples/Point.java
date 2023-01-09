import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Point {
    private double x;
    private double y;
    private double size = 5;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(double initX, double initY) {
        x = initX;
        y = initY;
    }

    public void draw(GraphicsContext pen) {
        pen.setFill(Color.BLACK);
        pen.fillOval(x, y, size, size);
    }
}
