import javafx.scene.canvas.GraphicsContext;

public class keyTypedExample extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }

    // Run this program to learn how each of these functions
    // relate to the others.
    public void setup(GraphicsContext pen) {}

    public void draw(GraphicsContext pen) {
    }

    public void keyPressed() {
        System.out.println("pressed " + (int)key.key + " " + key.keyCode);
    }

    public void keyTyped() {
        System.out.println("typed " + (int)key.key + " " + key.keyCode);
    }

    public void keyReleased() {
        System.out.println("released " + (int)key.key + " " + key.keyCode);
    }
}


