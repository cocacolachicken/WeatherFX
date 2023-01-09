import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class imageExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        Image myImage = new Image("noun-thumbs-up.png");
        pen.drawImage(myImage, 0, 0);
        pen.drawImage(myImage, 300, 300, 50, 50);
    }

    public void draw(GraphicsContext pen) {

    }
}

