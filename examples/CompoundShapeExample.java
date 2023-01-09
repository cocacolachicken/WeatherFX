import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CompoundShapeExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        pen.setStroke(Color.BLUE);
        pen.setFill(Color.PINK);
        pen.setLineWidth(10);
        pen.beginPath();
            pen.lineTo(100, 100);
            pen.arcTo(150, 50, 200, 100, 100);
            pen.arcTo(250, 50, 300, 100, 100);
        pen.fill();
        pen.closePath();
        pen.stroke();
    }

    public void draw(GraphicsContext pen) {

    }
}


/*
public void setup(GraphicsContext pen) {
        pen.setStroke(Color.RED);
        pen.setFill(Color.GREY);
        pen.setLineWidth(2.5);
        pen.beginPath();
            pen.lineTo(10, 10);
            pen.lineTo(300, 20);
            pen.lineTo(150, 60);
            pen.lineTo(50, 200);
            pen.fill();
        pen.closePath();
        pen.stroke();
    }
 */