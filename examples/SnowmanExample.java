import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnowmanExample extends ProcessingFX {
    int height = 300;
    Snowman frosty;

    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        window.setHeight(height + 100);
        window.setWidth(600);
    }

    public void draw(GraphicsContext pen) {
        window.setBackground(Color.rgb(250, 250, 250));
        pen.setFill(Color.BLUE);
        pen.fillRect(0,0, window.getWidth(), height);

        frosty = new Snowman(mouse.getX());
        frosty.draw(pen);
    }
}